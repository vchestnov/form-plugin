package com.form.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class FormLexer extends FlexAdapter {
    public FormLexer() {
        super(new _FormLexer((Reader) null));
    }
}
