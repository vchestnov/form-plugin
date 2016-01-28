package com.form.psi.impl;

import com.form.psi.FormExpression;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class FormExpressionImpl extends ASTWrapperPsiElement implements FormExpression {

    public FormExpressionImpl(ASTNode node) {
        super(node);
    }

}
