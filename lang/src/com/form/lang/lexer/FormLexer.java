package com.form.lang.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class FormLexer extends FlexAdapter {
    public final static int INITIAL = _FormLexer.YYINITIAL;

    public FormLexer() {
        super(new _FormLexer((Reader) null));
    }
}
