package com.form.lang.lexer;

import com.form.idea.FormLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FormToken extends IElementType {
    public FormToken(@NotNull @NonNls String debugName) {
        super(debugName, FormLanguage.INSTANCE);
    }
}
