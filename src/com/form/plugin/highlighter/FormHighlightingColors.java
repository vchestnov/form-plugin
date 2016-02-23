package com.form.plugin.highlighter;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FormHighlightingColors {
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("FORM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DIRECTIVE = createTextAttributesKey("FORM_DIRECTIVE", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("FORM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("FORM_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PARENTHESIS = createTextAttributesKey("FORM_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKETS = createTextAttributesKey("FORM_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
}
