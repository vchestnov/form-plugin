package com.form.plugin.highlighter;

import com.form.lexer.FormLexer;
import com.form.lexer.FormTokens;
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
        return new FormLexer();
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
        keys1.put(FormTokens.LINE_COMMENT, FormHighlightingColors.LINE_COMMENT);
    }
}
