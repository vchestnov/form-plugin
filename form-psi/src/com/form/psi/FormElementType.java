package com.form.psi;

import com.form.plugin.FormLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FormElementType extends IElementType {
    public FormElementType(@NotNull @NonNls String debugName) {
        super(debugName, FormLanguage.INSTANCE);
    }
}