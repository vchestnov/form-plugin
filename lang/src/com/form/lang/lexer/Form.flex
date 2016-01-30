package com.form.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%

%class _FormLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

DIGIT=[0-9]

LINE_COMMENT="*"[^\n]*
WHITE_SPACE_CHAR=[\ \n\t\f]

LETTER = [:letter:]
IDENTIFIER={LETTER}*

INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))

%%

({WHITE_SPACE_CHAR})+ { return FormTokens.WHITE_SPACE; }
^{LINE_COMMENT} { return FormTokens.LINE_COMMENT; }

"Symbols" { return FormTokens.SYMBOLS_KEYWORD; }
"Local" { return FormTokens.LOCAL_KEYWORD; }
"id" { return FormTokens.ID_KEYWORD; }
"Print" { return FormTokens.PRINT_KEYWORD; }
".end" { return FormTokens.END_KEYWORD; }

{INTEGER_LITERAL} { return FormTokens.INTEGER_LITERAL; }
{IDENTIFIER} { return FormTokens.IDENTIFIER; }

"("          { return FormTokens.LPAR      ; }
")"          { return FormTokens.RPAR      ; }
"["          { return FormTokens.LBRACKET  ; }
"]"          { return FormTokens.RBRACKET  ; }
"*"          { return FormTokens.MUL       ; }
"+"          { return FormTokens.PLUS      ; }
"-"          { return FormTokens.MINUS     ; }
"/"          { return FormTokens.DIV       ; }
"^"          { return FormTokens.POWER     ; }
"="          { return FormTokens.EQ        ; }
","          { return FormTokens.COMMA     ; }
";"          { return FormTokens.SEMICOLON ; }

. { return TokenType.BAD_CHARACTER; }