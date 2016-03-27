package com.form.lang.preprocessor

import com.form.lang.lexer.FormLexer
import com.form.lang.lexer.FormTokens.*
import com.intellij.lexer.LexerUtil
import java.util.*

class FormMacroSymbol(
        val name: String,
        val params: List<String>?,
        val substitution: String) {

    companion object Parser {
        fun parseFromDirectiveContent(content: CharSequence, offset: Int): FormMacroSymbol? {
            val contentLexer = FormLexer() // preprocessor should follow C rules in macros
            contentLexer.start(content)

            while (WHITE_SPACES.contains(contentLexer.tokenType)) {
                contentLexer.advance()
            }

            val tt = contentLexer.tokenType
            if (tt !== IDENTIFIER && !KEYWORDS.contains(tt)) return null;
            val name = LexerUtil.getTokenText(contentLexer).toString()
            contentLexer.advance()

            val params: MutableList<String>?
            if (contentLexer.tokenType === LPAR) {
                params = ArrayList<String>()
                contentLexer.advance()
                while (true) {
                    val t = contentLexer.tokenType
                    if (t !== COMMA && t !== IDENTIFIER && !KEYWORDS.contains(t) && !WHITE_SPACES.contains(t)) break

                    if (t === IDENTIFIER || KEYWORDS.contains(t)) {
                        var paramName = LexerUtil.getTokenText(contentLexer).toString()
                        contentLexer.advance()
                        params.add(paramName)
                    } else {
                        contentLexer.advance()
                    }
                }

                if (contentLexer.tokenType === RPAR) {
                    contentLexer.advance()
                }
            } else {
                params = null
            }

            while (WHITE_SPACES.contains(contentLexer.tokenType)) {
                contentLexer.advance()
            }

            if (contentLexer.tokenType === OPENING_QUOTE) contentLexer.advance()

            val replacement = StringBuilder()
            while (contentLexer.tokenType != CLOSING_QUOTE && contentLexer.tokenType != null) {
                val token = LexerUtil.getTokenText(contentLexer)
                replacement.append(token)
                contentLexer.advance()
            }

            return FormMacroSymbol(name, params, replacement.toString().trim())
        }
    }


}