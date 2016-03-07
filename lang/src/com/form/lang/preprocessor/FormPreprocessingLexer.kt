package com.form.lang.preprocessor

import com.form.lang.lexer.FormLexer
import com.form.lang.lexer.FormTokens.*
import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerPosition
import com.intellij.lexer.LexerUtil
import com.intellij.lexer.LookAheadLexer
import com.intellij.psi.TokenType

class FormPreprocessingLexer(private val myState: FormInclusionContext) : LookAheadLexer(FormLexer()) {


    override fun lookAhead(baseLexer: Lexer) {
        val baseToken = baseLexer.tokenType
        if (baseToken === DEFINE_DIRECTIVE) {
            processDefineDirective(baseLexer)
        } else if (baseToken == IFDEF_DIRECTIVE) {
            processIfDirective(baseLexer) { content: CharSequence ->
                val identifier = content.toString().trim().removePrefix("`").removeSuffix("'")
                myState.isDefined(identifier)
            }
        } else {
            super.lookAhead(baseLexer)
        }
    }

    private fun processDefineDirective(baseLexer: Lexer) {
        val offset = baseLexer.tokenStart

        advanceLexer(baseLexer)
        if (atDirectiveContent(baseLexer)) {
            val content = LexerUtil.getTokenText(baseLexer)
            addTokensFromDirective(baseLexer, content)
            val def = FormMacroSymbol.parseFromDirectiveContent(content, offset)
            if (def != null) {
                myState.define(def)
            }
        } else {
            addToken(baseLexer.tokenStart, END_OF_DIRECTIVE_CONTENT)
        }
    }

    private fun processIfDirective(lexer: Lexer, decisionEvaluator: (CharSequence) -> Boolean) {
        advanceLexer(lexer)
        if (atDirectiveContent(lexer)) {
            val content = LexerUtil.getTokenText(lexer)
            val decision = decisionEvaluator(content)
            addTokensFromDirective(lexer, content)

            if(decision){
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

    private fun skipConditionals(lexer: Lexer, stopOnElse: Boolean) {
        var nesting = 1

        while (WHITESPACES.contains(lexer.tokenType)) {
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

            if (WHITESPACES.contains(tt)) {
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

        while (WHITESPACES.contains(lexer.tokenType)) advanceAs(lexer, TokenType.WHITE_SPACE)
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
        val lexer = FormPreprocessingLexer(myState)
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
}
