package com.form.lang.lexer;

public class FormPreprocessorDirectiveToken extends FormSingleValueToken{
    public FormPreprocessorDirectiveToken(final String name) {
        super("FormPrepDirective: " + name, name);
    }
}
