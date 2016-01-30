package com.form.lang.psi;

import com.intellij.psi.PsiElement;

public interface FormElement extends PsiElement {
    void accept(FormVisitor visitor);
}
