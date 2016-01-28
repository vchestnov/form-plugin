package com.form.psi.impl;

import com.form.psi.FormBinaryExpression;
import com.intellij.lang.ASTNode;

public class FormBinaryExpressionImpl extends FormExpressionImpl implements FormBinaryExpression{
    public FormBinaryExpressionImpl(ASTNode node) {
        super(node);
    }
}
