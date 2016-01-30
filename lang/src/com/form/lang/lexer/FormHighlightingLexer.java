package com.form.lang.lexer;

import com.intellij.lexer.LayeredLexer;

public class FormHighlightingLexer extends LayeredLexer {
    public FormHighlightingLexer() {
        super(new FormLexer());
    }
}
