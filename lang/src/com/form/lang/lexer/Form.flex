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

LETTER = [:letter:]|_
IDENTIFIER_PART=[:digit:]|{LETTER}

PLAIN_IDENTIFIER={LETTER} {IDENTIFIER_PART}*
ESCAPED_IDENTIFIER=\[[^\[\n]+\]
IDENTIFIER={PLAIN_IDENTIFIER}|{ESCAPED_IDENTIFIER}

INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))

%%

({WHITE_SPACE_CHAR})+ { return FormTokens.WHITE_SPACE; }
^{LINE_COMMENT} { return FormTokens.LINE_COMMENT; }

"Symbols" { return FormTokens.SYMBOLS_KEYWORD; }
"Vectors" { return FormTokens.VECTORS_KEYWORD; }
"Indices" { return FormTokens.INDICES_KEYWORD; }
"Functions" { return FormTokens.FUNCTIONS_KEYWORD; }
"CFunctions" { return FormTokens.CFUNCTIONS_KEYWORD; }
"Tensors" { return FormTokens.TENSORS_KEYWORD; }
"CTensors" { return FormTokens.CTENSORS_KEYWORD; }
"NTensors" { return FormTokens.NTENSORS_KEYWORD; }
"Set" { return FormTokens.SET_KEYWORD; }

"if" { return FormTokens.IF_KEYWORD; }
"elseif" { return FormTokens.ELSEIF_KEYWORD; }
"else" { return FormTokens.ELSE_KEYWORD; }
"endif" { return FormTokens.ENDIF_KEYWORD; }

"Print" { return FormTokens.PRINT_KEYWORD; }
"Local" { return FormTokens.LOCAL_KEYWORD; }
"id" { return FormTokens.ID_KEYWORD; }


".clear"    { return FormTokens.CLEAR_KEYWORD; }
".store"    { return FormTokens.STORE_KEYWORD; }
".global"   { return FormTokens.GLOBAL_KEYWORD; }
".sort"     { return FormTokens.SORT_KEYWORD; }
".end"      { return FormTokens.END_KEYWORD; }

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
"=="         { return FormTokens.EQEQ      ; }
","          { return FormTokens.COMMA     ; }
";"          { return FormTokens.SEMICOLON ; }
"?"          { return FormTokens.QUEST     ; }

. { return TokenType.BAD_CHARACTER; }