package com.form.plugin;

import com.intellij.lang.Language;

public class FormLanguage extends Language {
    public static final FormLanguage INSTANCE = new FormLanguage();

    public FormLanguage() {
        super("Form", "text/form");
    }
}

