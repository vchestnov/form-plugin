package com.form.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface FormTokens {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    FormToken LINE_COMMENT = new FormToken("LINE_COMMENT");
    FormToken CONDITIONALLY_NON_COMPILED_COMMENT = new FormToken("CONDITIONALLY_NON_COMPILED_COMMENT");

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

    FormKeywordToken IF_KEYWORD = FormKeywordToken.keyword("if");
    FormKeywordToken ELSEIF_KEYWORD = FormKeywordToken.keyword("elseif");
    FormKeywordToken ELSE_KEYWORD = FormKeywordToken.keyword("else");
    FormKeywordToken ENDIF_KEYWORD = FormKeywordToken.keyword("endif");

    FormKeywordToken CLEAR_KEYWORD = FormKeywordToken.keyword(".clear");
    FormKeywordToken STORE_KEYWORD = FormKeywordToken.keyword(".store");
    FormKeywordToken GLOBAL_KEYWORD = FormKeywordToken.keyword(".global");
    FormKeywordToken SORT_KEYWORD = FormKeywordToken.keyword(".sort");
    FormKeywordToken END_KEYWORD = FormKeywordToken.keyword(".end");

    FormPreprocessorDirectiveToken ADD_DIRECTIVE = new FormPreprocessorDirectiveToken("#ADD");
    FormPreprocessorDirectiveToken ADDSEPARATOR_DIRECTIVE = new FormPreprocessorDirectiveToken("#ADD_SEPARATOR");
    FormPreprocessorDirectiveToken APPEND_DIRECTIVE = new FormPreprocessorDirectiveToken("#APPEND");
    FormPreprocessorDirectiveToken BREAK_DIRECTIVE = new FormPreprocessorDirectiveToken("#BREAK");
    FormPreprocessorDirectiveToken BREAKDO_DIRECTIVE = new FormPreprocessorDirectiveToken("#BREAK_DO");
    FormPreprocessorDirectiveToken CALL_DIRECTIVE = new FormPreprocessorDirectiveToken("#CALL");
    FormPreprocessorDirectiveToken CASE_DIRECTIVE = new FormPreprocessorDirectiveToken("#CASE");
    FormPreprocessorDirectiveToken CLEAROPTIMIZE_DIRECTIVE = new FormPreprocessorDirectiveToken("#CLEAROPTIMIZE");
    FormPreprocessorDirectiveToken CLOSE_DIRECTIVE = new FormPreprocessorDirectiveToken("#CLOSE");
    FormPreprocessorDirectiveToken CLOSEDICTIONARY_DIRECTIVE = new FormPreprocessorDirectiveToken("#CLOSEDICTIONARY");
    FormPreprocessorDirectiveToken COMMENTCHAR_DIRECTIVE = new FormPreprocessorDirectiveToken("#COMMENTCHAR");
    FormPreprocessorDirectiveToken CREATE_DIRECTIVE = new FormPreprocessorDirectiveToken("#CREATE");
    FormPreprocessorDirectiveToken DEFAULT_DIRECTIVE = new FormPreprocessorDirectiveToken("#DEFAULT");
    FormPreprocessorDirectiveToken DEFINE_DIRECTIVE = new FormPreprocessorDirectiveToken("#DEFINE");
    FormPreprocessorDirectiveToken DO_DIRECTIVE = new FormPreprocessorDirectiveToken("#DO");
    FormPreprocessorDirectiveToken ELSE_DIRECTIVE = new FormPreprocessorDirectiveToken("#ELSE");
    FormPreprocessorDirectiveToken ELSEIF_DIRECTIVE = new FormPreprocessorDirectiveToken("#ELSEIF");
    FormPreprocessorDirectiveToken ENDDO_DIRECTIVE = new FormPreprocessorDirectiveToken("#ENDDO");
    FormPreprocessorDirectiveToken ENDIF_DIRECTIVE = new FormPreprocessorDirectiveToken("#ENDIF");
    FormPreprocessorDirectiveToken ENDINSIDE_DIRECTIVE = new FormPreprocessorDirectiveToken("#ENDINSIDE");
    FormPreprocessorDirectiveToken ENDPROCEDURE_DIRECTIVE = new FormPreprocessorDirectiveToken("#ENDPROCEDURE");
    FormPreprocessorDirectiveToken ENDSWITCH_DIRECTIVE = new FormPreprocessorDirectiveToken("#ENDSWITCH");
    FormPreprocessorDirectiveToken EXCHANGE_DIRECTIVE = new FormPreprocessorDirectiveToken("#EXCHANGE");
    FormPreprocessorDirectiveToken EXTERNAL_DIRECTIVE = new FormPreprocessorDirectiveToken("#EXTERNAL");
    FormPreprocessorDirectiveToken FACTDOLLAR_DIRECTIVE = new FormPreprocessorDirectiveToken("#FACTDOLLAR");
    FormPreprocessorDirectiveToken FROMEXTERNAL_DIRECTIVE = new FormPreprocessorDirectiveToken("#FROMEXTERNAL");
    FormPreprocessorDirectiveToken IF_DIRECTIVE = new FormPreprocessorDirectiveToken("#IF");
    FormPreprocessorDirectiveToken IFDEF_DIRECTIVE = new FormPreprocessorDirectiveToken("#IFDEF");
    FormPreprocessorDirectiveToken IFNDEF_DIRECTIVE = new FormPreprocessorDirectiveToken("#IFNDEF");
    FormPreprocessorDirectiveToken INCLUDE_DIRECTIVE = new FormPreprocessorDirectiveToken("#INCLUDE");
    FormPreprocessorDirectiveToken INSIDE_DIRECTIVE = new FormPreprocessorDirectiveToken("#INSIDE");
    FormPreprocessorDirectiveToken MESSAGE_DIRECTIVE = new FormPreprocessorDirectiveToken("#MESSAGE");
    FormPreprocessorDirectiveToken OPENDICTIONARY_DIRECTIVE = new FormPreprocessorDirectiveToken("#OPENDICTIONARY");
    FormPreprocessorDirectiveToken OPTIMIZE_DIRECTIVE = new FormPreprocessorDirectiveToken("#OPTIMIZE");
    FormPreprocessorDirectiveToken PIPE_DIRECTIVE = new FormPreprocessorDirectiveToken("#PIPE");
    FormPreprocessorDirectiveToken PREOUT_DIRECTIVE = new FormPreprocessorDirectiveToken("#PREOUT");
    FormPreprocessorDirectiveToken PRINTTIMES_DIRECTIVE = new FormPreprocessorDirectiveToken("#PRINTTIMES");
    FormPreprocessorDirectiveToken PROCEDURE_DIRECTIVE = new FormPreprocessorDirectiveToken("#PROCEDURE");
    FormPreprocessorDirectiveToken PROCEDUREEXTENSION_DIRECTIVE = new FormPreprocessorDirectiveToken("#PROCEDUREEXTENSION");
    FormPreprocessorDirectiveToken PROMPT_DIRECTIVE = new FormPreprocessorDirectiveToken("#PROMPT");
    FormPreprocessorDirectiveToken REDEFINE_DIRECTIVE = new FormPreprocessorDirectiveToken("#REDEFINE");
    FormPreprocessorDirectiveToken REMOVE_DIRECTIVE = new FormPreprocessorDirectiveToken("#REMOVE");
    FormPreprocessorDirectiveToken RESET_DIRECTIVE = new FormPreprocessorDirectiveToken("#RESET");
    FormPreprocessorDirectiveToken REVERSEINCLUDE_DIRECTIVE = new FormPreprocessorDirectiveToken("#REVERSEINCLUDE");
    FormPreprocessorDirectiveToken RMEXTERNAL_DIRECTIVE = new FormPreprocessorDirectiveToken("#RMEXTERNAL");
    FormPreprocessorDirectiveToken RMSEPARATOR_DIRECTIVE = new FormPreprocessorDirectiveToken("#RMSEPARATOR");
    FormPreprocessorDirectiveToken SETEXTERNAL_DIRECTIVE = new FormPreprocessorDirectiveToken("#SETEXTERNAL");
    FormPreprocessorDirectiveToken SETEXTERNALATTR_DIRECTIVE = new FormPreprocessorDirectiveToken("#SETEXTERNALATTR");
    FormPreprocessorDirectiveToken SETRANDOM_DIRECTIVE = new FormPreprocessorDirectiveToken("#SETRANDOM");
    FormPreprocessorDirectiveToken SHOW_DIRECTIVE = new FormPreprocessorDirectiveToken("#SHOW");
    FormPreprocessorDirectiveToken SKIPEXTRASYMBOLS_DIRECTIVE = new FormPreprocessorDirectiveToken("#SKIPEXTRASYMBOLS");
    FormPreprocessorDirectiveToken SWITCH_DIRECTIVE = new FormPreprocessorDirectiveToken("#SWITCH");
    FormPreprocessorDirectiveToken SYSTEM_DIRECTIVE = new FormPreprocessorDirectiveToken("#SYSTEM");
    FormPreprocessorDirectiveToken TERMINATE_DIRECTIVE = new FormPreprocessorDirectiveToken("#TERMINATE");
    FormPreprocessorDirectiveToken TOEXTERNAL_DIRECTIVE = new FormPreprocessorDirectiveToken("#TOEXTERNAL");
    FormPreprocessorDirectiveToken UNDEFINE_DIRECTIVE = new FormPreprocessorDirectiveToken("#UNDEFINE");
    FormPreprocessorDirectiveToken USEDICTIONARY_DIRECTIVE = new FormPreprocessorDirectiveToken("#USEDICTIONARY");
    FormPreprocessorDirectiveToken WRITE_DIRECTIVE = new FormPreprocessorDirectiveToken("#WRITE");


    FormToken IDENTIFIER = new FormToken("IDENTIFIER");

    FormToken DIRECTIVE_CONTENT = new FormToken("DIRECTIVE_CONTENT");
    FormToken END_OF_DIRECTIVE_CONTENT = new FormToken("END_OF_DIRECTIVE_CONTENT");
    FormToken CLOSING_QUOTE = new FormToken("CLOSING_QUOTE");
    FormToken OPEN_QUOTE = new FormToken("OPEN_QUOTE");
    FormToken REGULAR_STRING_PART = new FormToken("REGULAR_STRING_PART");

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
    FormSingleValueToken EQEQ = new FormSingleValueToken("EQEQ", "==");
    FormSingleValueToken QUEST = new FormSingleValueToken("QUEST", "?");
    FormSingleValueToken TYLDA = new FormSingleValueToken("QUEST", "?");
    FormSingleValueToken QUOTE = new FormSingleValueToken("QUOTE", "'");
    FormSingleValueToken BACKQUOTE = new FormSingleValueToken("BACKQUOTE", "`");

    FormSingleValueToken COMMA = new FormSingleValueToken("COMMA", ",");
    FormSingleValueToken SEMICOLON = new FormSingleValueToken("SEMICOLON", ";");

    TokenSet KEYWORDS = TokenSet.create(
            SYMBOLS_KEYWORD, FUNCTIONS_KEYWORD, CFUNCTIONS_KEYWORD, VECTORS_KEYWORD, TENSORS_KEYWORD, CTENSORS_KEYWORD,
            NTENSORS_KEYWORD, SET_KEYWORD, INDICES_KEYWORD,
            END_KEYWORD, SORT_KEYWORD, GLOBAL_KEYWORD, STORE_KEYWORD, CLEAR_KEYWORD,
            LOCAL_KEYWORD, ID_KEYWORD, PRINT_KEYWORD,
            IF_KEYWORD, ELSEIF_KEYWORD, ELSE_KEYWORD, ENDIF_KEYWORD);

    TokenSet DIRECTIVES = TokenSet.create(
            ADD_DIRECTIVE, ADDSEPARATOR_DIRECTIVE, APPEND_DIRECTIVE, BREAK_DIRECTIVE, BREAKDO_DIRECTIVE, CALL_DIRECTIVE,
            CASE_DIRECTIVE, CLEAROPTIMIZE_DIRECTIVE, CLOSE_DIRECTIVE, CLOSEDICTIONARY_DIRECTIVE, COMMENTCHAR_DIRECTIVE,
            CREATE_DIRECTIVE, DEFAULT_DIRECTIVE, DEFINE_DIRECTIVE, DO_DIRECTIVE, ELSE_DIRECTIVE, ELSEIF_DIRECTIVE,
            ENDDO_DIRECTIVE, ENDIF_DIRECTIVE, ENDINSIDE_DIRECTIVE, ENDPROCEDURE_DIRECTIVE, ENDSWITCH_DIRECTIVE,
            EXCHANGE_DIRECTIVE, EXTERNAL_DIRECTIVE, FACTDOLLAR_DIRECTIVE, FROMEXTERNAL_DIRECTIVE, IF_DIRECTIVE,
            IFDEF_DIRECTIVE, IFNDEF_DIRECTIVE, INCLUDE_DIRECTIVE, INSIDE_DIRECTIVE, MESSAGE_DIRECTIVE, OPENDICTIONARY_DIRECTIVE,
            OPTIMIZE_DIRECTIVE, PIPE_DIRECTIVE, PREOUT_DIRECTIVE, PRINTTIMES_DIRECTIVE, PROCEDURE_DIRECTIVE, PROCEDUREEXTENSION_DIRECTIVE,
            PROMPT_DIRECTIVE, REDEFINE_DIRECTIVE, REMOVE_DIRECTIVE, RESET_DIRECTIVE, REVERSEINCLUDE_DIRECTIVE, RMEXTERNAL_DIRECTIVE,
            RMSEPARATOR_DIRECTIVE, SETEXTERNAL_DIRECTIVE, SETEXTERNALATTR_DIRECTIVE, SETRANDOM_DIRECTIVE, SHOW_DIRECTIVE,
            SKIPEXTRASYMBOLS_DIRECTIVE, SWITCH_DIRECTIVE, SYSTEM_DIRECTIVE, TERMINATE_DIRECTIVE, TOEXTERNAL_DIRECTIVE,
            UNDEFINE_DIRECTIVE, USEDICTIONARY_DIRECTIVE, WRITE_DIRECTIVE);

    TokenSet END_IF_DIRECTIVES = TokenSet.create(ENDIF_DIRECTIVE, ELSE_DIRECTIVE, ELSEIF_DIRECTIVE);

    TokenSet OPERATIONS = TokenSet.create(PLUS, MINUS, MUL, DIV, POWER);
    TokenSet BINARY_OPERATIONS = TokenSet.create(PLUS, MINUS, MUL, DIV, POWER);
    TokenSet WHITE_SPACES = TokenSet.create(WHITE_SPACE);
    TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, CONDITIONALLY_NON_COMPILED_COMMENT);

    TokenSet TYPES = TokenSet.create(SYMBOLS_KEYWORD, FUNCTIONS_KEYWORD, CFUNCTIONS_KEYWORD, VECTORS_KEYWORD, TENSORS_KEYWORD, CTENSORS_KEYWORD,
            NTENSORS_KEYWORD, SET_KEYWORD, INDICES_KEYWORD);

    TokenSet MODULE_INSTRUCTIONS = TokenSet.create(END_KEYWORD, SORT_KEYWORD, GLOBAL_KEYWORD, STORE_KEYWORD, CLEAR_KEYWORD);

    TokenSet ALL_ASSIGNMENTS = TokenSet.create(EQ);
}
