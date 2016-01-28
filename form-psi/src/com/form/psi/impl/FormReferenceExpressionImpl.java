package com.form.psi.impl;

import com.form.psi.FormReferenceExpression;
import com.intellij.lang.ASTNode;

public class FormReferenceExpressionImpl extends FormExpressionImpl implements FormReferenceExpression {
    public FormReferenceExpressionImpl(ASTNode node) {
        super(node);
    }
}
