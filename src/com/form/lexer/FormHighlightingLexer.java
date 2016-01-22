package com.form.lexer;

import com.intellij.lexer.LayeredLexer;
import com.intellij.lexer.Lexer;

public class FormHighlightingLexer extends LayeredLexer {
    public FormHighlightingLexer() {
        super(new FormLexer());
    }
}
