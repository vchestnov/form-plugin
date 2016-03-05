package com.form.lang.preprocessor

import com.form.lang.lexer.FormLexer
import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerUtil
import com.intellij.lexer.LookAheadLexer
import com.intellij.psi.tree.IElementType

import com.form.lang.lexer.FormTokens.*

class FormPreprocessingLexer(private val myState: FormInclusionContext) : LookAheadLexer(FormLexer()) {

    override fun lookAhead(baseLexer: Lexer) {
        val baseToken = baseLexer.tokenType
        if (baseToken === DEFINE_DIRECTIVE) {
            processDefineDirective(baseLexer)
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
