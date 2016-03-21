package com.form.lang.preprocessor

import com.form.lang.lexer.FormLexer
import com.form.lang.lexer.FormTokens.*
import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerPosition
import com.intellij.lexer.LexerUtil
import com.intellij.lexer.LookAheadLexer
import com.intellij.openapi.util.TextRange
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.util.containers.hash.HashMap
import java.util.*

class FormPreprocessingLexer(
        private val state: FormInclusionContext,
        private val addTokensFromDirective: Boolean = false
) : LookAheadLexer(FormLexer()) {
    var macroLevel = 0


    override fun lookAhead(baseLexer: Lexer) {
        val baseToken = baseLexer.tokenType
        if (baseToken == BACKQUOTE) {
            processMacroSubstitution(baseLexer)
        } else if (baseToken === DEFINE_DIRECTIVE || baseToken === REDEFINE_DIRECTIVE) {
            processDefineDirective(baseLexer, baseToken === REDEFINE_DIRECTIVE)
        } else if (baseToken === UNDEFINE_DIRECTIVE) {
            processUndefineDirective(baseLexer)
        } else if (baseToken == IFDEF_DIRECTIVE) {
            processIfDirective(baseLexer) { content: CharSequence ->
                val identifier = content.toString().trim().removePrefix("`").removeSuffix("'")
                state.isDefined(identifier)
            }
        } else if (baseToken == IFNDEF_DIRECTIVE) {
            processIfDirective(baseLexer) { content: CharSequence ->
                val identifier = content.toString().trim().removePrefix("`").removeSuffix("'")
                state.isUndefined(identifier)
            }
        } else {
            super.lookAhead(baseLexer)
        }
    }

    private fun processMacroSubstitution(baseLexer: Lexer) {
        skipMacroToken(baseLexer, false, false) //skip backquote

        val macro: FormMacroSymbol?
        if (baseLexer.tokenType == IDENTIFIER) {
            val id = baseLexer.tokenText
            macro = state.getDefinition(id)
            skipMacroToken(baseLexer, false, true)
            buildSubstitutionArguments(baseLexer)
        } else {
            macro = null
        }

        if (baseLexer.tokenType == QUOTE) {
            skipMacroToken(baseLexer, false, false)
        }

        if (macro == null) return
        if (addTokensFromDirective) return

        val substLexer = FormPreprocessingLexer(state)
        val substString = macro.substitution
        substLexer.start(substString, 0, substString.length)
        while (true) {
            val type = substLexer.tokenType ?: break
            if (WHITE_SPACES.contains(type)) {
                addToken(baseLexer.tokenStart, type)
            } else {
                val tokenText = LexerUtil.getTokenText(substLexer)
                val leafNode = FormMacroForeignLeafType(type, tokenText, macro.name)
                addToken(baseLexer.tokenStart, leafNode)
            }
            substLexer.advance()
        }
    }

    private fun buildSubstitutionArguments(baseLexer: Lexer): List<String>? {
        val args = ArrayList<String>()
        val pos = baseLexer.currentPosition // save, ( can absent

        // go ahead for (
        while (WHITE_SPACES.contains(baseLexer.tokenType)) {
            baseLexer.advance()
        }

        if (baseLexer.tokenType === LPAR) {
            skipMacroToken(baseLexer, false, false) // skip LPAR

            val builder = StringBuilder()

            while (true) {
                val tt = baseLexer.tokenType
                if (tt == null || tt == RPAR) break

                if (tt === COMMA) {
                    args.add(builder.toString())
                    builder.setLength(0)
                    skipMacroToken(baseLexer, false, false) // skip COMMA
                } else {
                    var text = LexerUtil.getTokenText(baseLexer)
                    builder.append(text)
                    skipMacroToken(baseLexer, true, false)
                }
            }

            args.add(builder.toString())
            skipMacroToken(baseLexer, false, false)
            return args
        } else {
            baseLexer.restore(pos)
            return null
        }
    }

    private fun processDefineDirective(baseLexer: Lexer, redefine: Boolean) {
        val offset = baseLexer.tokenStart

        advanceLexer(baseLexer)
        if (atDirectiveContent(baseLexer)) {
            val content = LexerUtil.getTokenText(baseLexer)
            addTokensFromDirective(baseLexer, content)
            val def = FormMacroSymbol.parseFromDirectiveContent(content, offset)
            if (def != null) {
                if (redefine) {
                    state.redefine(def)
                } else {
                    state.define(def)
                }
            }
        } else {
            addToken(baseLexer.tokenStart, END_OF_DIRECTIVE_CONTENT)
        }
    }

    private fun buildParameterSubstitutionMap(parameterNames: List<String>?, args: List<String>?): Map<String, String> {
        if (parameterNames == null || parameterNames.size == 0) return emptyMap()

        val paramSubst = HashMap<String, String>()
        parameterNames.forEachIndexed { ind, name ->
            paramSubst.put(name, args?.getOrNull(ind) ?: "")
        }
        return paramSubst
    }

    private fun processUndefineDirective(lexer: Lexer) {
        advanceLexer(lexer)
        if (atDirectiveContent(lexer)) {
            val contents = LexerUtil.getTokenText(lexer)
            addTokensFromDirective(lexer, contents)

            val l = FormLexer()
            l.start(contents)
            while (WHITE_SPACES.contains(l.tokenType)) {
                l.advance()
            }
            if (l.tokenType === IDENTIFIER) {
                state.undef(LexerUtil.getTokenText(l).toString())
            }
        } else {
            addToken(lexer.tokenStart, END_OF_DIRECTIVE_CONTENT)
        }
    }

    private fun processIfDirective(lexer: Lexer, decisionEvaluator: (CharSequence) -> Boolean) {
        advanceLexer(lexer)
        if (atDirectiveContent(lexer)) {
            val content = LexerUtil.getTokenText(lexer)
            val decision = decisionEvaluator(content)
            addTokensFromDirective(lexer, content)

            if (decision) {
                processConditionals(lexer)
                val tt = lexer.tokenType
                if (tt === ELSE_DIRECTIVE || tt === ELSEIF_DIRECTIVE) {
                    skipDirectiveWithContent(lexer)
                    skipConditionals(lexer, false)
                }
            } else {
                skipConditionals(lexer, true)

                val tt = lexer.tokenType
                if (tt === ELSE_DIRECTIVE) {
                    skipDirectiveWithContent(lexer)

                    processConditionals(lexer)
                }
            }
        } else {
            addToken(lexer.tokenStart, END_OF_DIRECTIVE_CONTENT)
        }
    }

    private fun processConditionals(lexer: Lexer) {
        while (true) {
            val tt = lexer.tokenType
            if (tt == null || END_IF_DIRECTIVES.contains(tt)) {
                break
            }

            lookAhead(lexer)
        }
    }

    private fun skipMacroToken(baseLexer: Lexer, isParamToken: Boolean, isRoot: Boolean): FormMacroReferenceTokenType? {
        var result: FormMacroReferenceTokenType? = null
        val baseToken = baseLexer.tokenType
        if (baseToken != null) {
            if (WHITE_SPACES.contains(baseToken)) {
                advanceAs(baseLexer, baseToken) // whitespaces have the special logic for re-balancing in PsiBuilderImpl
            } else {
                val text = LexerUtil.getTokenText(baseLexer)
                val preprocessed: IElementType
                preprocessed = if (isRoot && (KEYWORDS.contains(baseToken))) IDENTIFIER else baseToken
                result = FormMacroReferenceTokenType(preprocessed, text, isParamToken, macroLevel, isRoot)
                advanceAs(baseLexer, result)
            }
        }

        return result
    }

    private fun skipConditionals(lexer: Lexer, stopOnElse: Boolean) {
        var nesting = 1

        while (WHITE_SPACES.contains(lexer.tokenType)) {
            //            adjustLineCount(LexerUtil.getTokenText(lexer))
            advanceAs(lexer, TokenType.WHITE_SPACE)
        }

        var beforeEnd: LexerPosition? = null
        while (true) {
            val tt = lexer.tokenType ?: break

            if (tt === IF_DIRECTIVE || tt === IFDEF_DIRECTIVE || tt === IFNDEF_DIRECTIVE) {
                nesting++
            } else if (stopOnElse && (tt === ELSEIF_DIRECTIVE || tt === ELSE_DIRECTIVE) || tt === ENDIF_DIRECTIVE) {
                nesting--
                if (nesting == 0) break
                if (tt !== ENDIF_DIRECTIVE) nesting++
            }

            if (WHITE_SPACES.contains(tt)) {
                //                adjustLineCount(LexerUtil.getTokenText(lexer))
            } else {
                beforeEnd = lexer.currentPosition
            }

            lexer.advance()
        }

        if (beforeEnd != null) {
            lexer.restore(beforeEnd)
            advanceAs(lexer, CONDITIONALLY_NON_COMPILED_COMMENT)
        }

        while (WHITE_SPACES.contains(lexer.tokenType)) advanceAs(lexer, TokenType.WHITE_SPACE)
    }

    private fun skipDirectiveWithContent(lexer: Lexer) {
        val tt = lexer.tokenType
        advanceLexer(lexer)
        if (atDirectiveContent(lexer)) {
            val content = LexerUtil.getTokenText(lexer)
            if (tt === ELSEIF_DIRECTIVE) {
                addTokensFromDirective(lexer, content)
            } else {
                //                adjustLineCount(content)
                advanceLexer(lexer)
            }
        } else {
            addToken(lexer.tokenStart, END_OF_DIRECTIVE_CONTENT)
        }
    }

    private fun addTokensFromDirective(baseLexer: Lexer, content: CharSequence) {
        val lexer = FormPreprocessingLexer(state, addTokensFromDirective = true)
        lexer.start(content, 0, content.length, FormLexer.INITIAL)

        val tokenStart = baseLexer.tokenStart
        while (lexer.tokenType != null) {
            val originToken = lexer.tokenType

            //Treat c/c++/objc keywords as identifiers while parsing macros!
            val mappedToken = if (KEYWORDS.contains(originToken)) IDENTIFIER else originToken

            addToken(tokenStart + lexer.tokenEnd, mappedToken)
            lexer.advance()
        }
        addToken(baseLexer.tokenEnd, END_OF_DIRECTIVE_CONTENT)
        baseLexer.advance()
    }

    private fun atDirectiveContent(lexer: Lexer): Boolean {
        val tt = lexer.tokenType
        return tt === DIRECTIVE_CONTENT
    }

    private class SubstitutionResult {
        private val _string = StringBuilder()
        val string: String
            get() = _string.toString()

        private val _substitutions = ArrayList<Pair<Int, TextRange>>()
        val substitutions: List<Pair<Int, TextRange>> = _substitutions

        fun append(string: String) {
            _string.append(string)
        }

        private fun addSubstitution(argumentIndex: Int, substLength: Int) {
            _substitutions.add(Pair(argumentIndex, TextRange(_string.length, _string.length + substLength)))
        }

        fun removeLastComma() {
            var lastIndex = _string.length - 1
            while (lastIndex >= 0 && _string[lastIndex] == ' ') lastIndex--
            if (lastIndex >= 0 && _string[lastIndex] == ',') {
                _string.setLength(lastIndex)
            }
        }
    }
}
