package com.form.plugin.highlighter;

import com.form.lang.lexer.FormHighlightingLexer;
import com.form.lang.lexer.FormTokens;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FormHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FormHighlightingLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys1.get(tokenType), keys2.get(tokenType));
    }

    static {
        keys1 = new HashMap<>();
        keys2 = new HashMap<>();

        fillMap(keys1, FormTokens.KEYWORDS, FormHighlightingColors.KEYWORD);
        fillMap(keys1, FormTokens.DIRECTIVES, FormHighlightingColors.DIRECTIVE);
        keys1.put(FormTokens.LINE_COMMENT, FormHighlightingColors.LINE_COMMENT);
        keys1.put(FormTokens.INTEGER_LITERAL, FormHighlightingColors.NUMBER);
        keys1.put(FormTokens.LPAR, FormHighlightingColors.PARENTHESIS);
        keys1.put(FormTokens.RPAR, FormHighlightingColors.PARENTHESIS);
        keys1.put(FormTokens.LBRACKET, FormHighlightingColors.BRACKETS);
        keys1.put(FormTokens.RBRACKET, FormHighlightingColors.BRACKETS);
    }
}
