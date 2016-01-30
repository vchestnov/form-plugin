package com.form.lang.psi.impl;

import com.form.lang.psi.FormElement;
import com.form.lang.psi.FormVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class FormElementImpl extends ASTWrapperPsiElement implements FormElement {

    public FormElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof FormVisitor) {
            accept((FormVisitor) visitor);
        }
        else {
            visitor.visitElement(this);
        }
    }

    @Override
    public void accept(FormVisitor visitor) {
        visitor.visitFormElement(this);
    }
}
