package com.form.lexer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Sem on 22.01.2016.
 */
public class FormSingleValueToken extends FormToken {
    private final String value;

    public FormSingleValueToken(@NotNull @NonNls String debugName, @NotNull @NonNls String value) {
        super(debugName);
        this.value = value;
    }

    @NotNull @NonNls
    public String getValue() {
        return value;
    }
}
