package com.form.lang.psi.impl;

import com.form.lang.psi.FormExpression;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class FormExpressionImpl extends FormElementImpl implements FormExpression {

    public FormExpressionImpl(ASTNode node) {
        super(node);
    }

}
