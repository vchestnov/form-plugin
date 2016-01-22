package com.form.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface FormTokens {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

    FormKeywordToken SYMBOLS_KEYWORD = FormKeywordToken.keyword("Symbols");
    FormKeywordToken LOCAL_KEYWORD = FormKeywordToken.keyword("Local");
    FormKeywordToken PRINT_KEYWORD = FormKeywordToken.keyword("Print");
    FormKeywordToken END_KEYWORD = FormKeywordToken.keyword(".end");

    TokenSet KEYWORDS = TokenSet.create(SYMBOLS_KEYWORD, LOCAL_KEYWORD, PRINT_KEYWORD, END_KEYWORD);
}
