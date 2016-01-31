package com.form.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface FormTokens {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    FormToken LINE_COMMENT = new FormToken("LINE_COMMENT");

    FormToken INTEGER_LITERAL = new FormToken("INTEGER_LITERAL");

    FormKeywordToken SYMBOLS_KEYWORD = FormKeywordToken.keyword("Symbols");
    FormKeywordToken FUNCTIONS_KEYWORD = FormKeywordToken.keyword("Functions");
    FormKeywordToken CFUNCTIONS_KEYWORD = FormKeywordToken.keyword("CFunctions");
    FormKeywordToken VECTORS_KEYWORD = FormKeywordToken.keyword("Vectors");
    FormKeywordToken TENSORS_KEYWORD = FormKeywordToken.keyword("Tensors");
    FormKeywordToken CTENSORS_KEYWORD = FormKeywordToken.keyword("CTensors");
    FormKeywordToken NTENSORS_KEYWORD = FormKeywordToken.keyword("NTensors");
    FormKeywordToken SET_KEYWORD = FormKeywordToken.keyword("Set");
    FormKeywordToken INDICES_KEYWORD = FormKeywordToken.keyword("Indices");

    FormKeywordToken LOCAL_KEYWORD = FormKeywordToken.keyword("Local");
    FormKeywordToken ID_KEYWORD = FormKeywordToken.keyword("id");
    FormKeywordToken PRINT_KEYWORD = FormKeywordToken.keyword("Print");

    FormKeywordToken CLEAR_KEYWORD = FormKeywordToken.keyword(".clear");
    FormKeywordToken STORE_KEYWORD = FormKeywordToken.keyword(".store");
    FormKeywordToken GLOBAL_KEYWORD = FormKeywordToken.keyword(".global");
    FormKeywordToken SORT_KEYWORD = FormKeywordToken.keyword(".sort");
    FormKeywordToken END_KEYWORD = FormKeywordToken.keyword(".end");

    FormToken IDENTIFIER = new FormToken("IDENTIFIER");

    FormSingleValueToken LBRACKET = new FormSingleValueToken("LBRACKET", "[");
    FormSingleValueToken RBRACKET = new FormSingleValueToken("RBRACKET", "]");
    FormSingleValueToken LPAR = new FormSingleValueToken("LPAR", "(");
    FormSingleValueToken RPAR = new FormSingleValueToken("RPAR", ")");
    FormSingleValueToken PLUS = new FormSingleValueToken("PLUS", "+");
    FormSingleValueToken MUL = new FormSingleValueToken("MUL", "*");
    FormSingleValueToken MINUS = new FormSingleValueToken("MINUS", "-");
    FormSingleValueToken DIV = new FormSingleValueToken("DIV", "/");
    FormSingleValueToken POWER = new FormSingleValueToken("POWER", "^");
    FormSingleValueToken EQ = new FormSingleValueToken("EQ", "=");
    FormSingleValueToken QUEST = new FormSingleValueToken("QUEST", "?");

    FormSingleValueToken COMMA = new FormSingleValueToken("COMMA", ",");
    FormSingleValueToken SEMICOLON   = new FormSingleValueToken("SEMICOLON", ";");

    TokenSet KEYWORDS = TokenSet.create(
            SYMBOLS_KEYWORD, FUNCTIONS_KEYWORD, CFUNCTIONS_KEYWORD, VECTORS_KEYWORD, TENSORS_KEYWORD, CTENSORS_KEYWORD,
            NTENSORS_KEYWORD, SET_KEYWORD, INDICES_KEYWORD,
            END_KEYWORD, SORT_KEYWORD, GLOBAL_KEYWORD, STORE_KEYWORD, CLEAR_KEYWORD,
            LOCAL_KEYWORD, ID_KEYWORD, PRINT_KEYWORD);
    TokenSet OPERATIONS = TokenSet.create(PLUS, MINUS, MUL, DIV, POWER);
    TokenSet BINARY_OPERATIONS = TokenSet.create(PLUS, MINUS, MUL, DIV, POWER);
    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet COMMENTS = TokenSet.create(LINE_COMMENT);

    TokenSet TYPES = TokenSet.create(SYMBOLS_KEYWORD, FUNCTIONS_KEYWORD, CFUNCTIONS_KEYWORD, VECTORS_KEYWORD, TENSORS_KEYWORD, CTENSORS_KEYWORD,
            NTENSORS_KEYWORD, SET_KEYWORD, INDICES_KEYWORD);

    TokenSet MODULE_INSTRUCTIONS = TokenSet.create(END_KEYWORD, SORT_KEYWORD, GLOBAL_KEYWORD, STORE_KEYWORD, CLEAR_KEYWORD);

    TokenSet ALL_ASSIGNMENTS = TokenSet.create(EQ);
}
