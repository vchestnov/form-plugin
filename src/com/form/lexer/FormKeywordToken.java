package com.form.lexer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FormKeywordToken extends FormSingleValueToken {

    /**
     * Generate keyword (identifier that has a keyword meaning in all possible contexts)
     */
    public static FormKeywordToken keyword(String value) {
        return keyword(value, value);
    }

    public static FormKeywordToken keyword(String debugName, String value) {
        return new FormKeywordToken(debugName, value, false);
    }

    /**
     * Generate soft keyword (identifier that has a keyword meaning only in some contexts)
     */
    public static FormKeywordToken softKeyword(String value) {
        return new FormKeywordToken(value, value, true);
    }

    private final boolean myIsSoft;

    protected FormKeywordToken(@NotNull @NonNls String debugName, @NotNull @NonNls String value, boolean isSoft) {
        super(debugName, value);
        myIsSoft = isSoft;
    }

    public boolean isSoft() {
        return myIsSoft;
    }
}