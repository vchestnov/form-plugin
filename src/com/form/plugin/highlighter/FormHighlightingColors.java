package com.form.plugin.highlighter;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FormHighlightingColors {
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("FORM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("FORM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
}