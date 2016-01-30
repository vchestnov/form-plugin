// This is a generated file. Not intended for manual editing.
package com.form.lang.psi;

import com.form.idea.FormLanguage;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.form.lang.psi.impl.*;
import com.intellij.psi.tree.IFileElementType;

public interface FormElementTypes {
    IFileElementType FORM_FILE = new IFileElementType(FormLanguage.INSTANCE);

    FormElementType PRINT_STATEMENT = new FormElementType("PRINT");

    FormElementType SYMBOL = new FormElementType("SYMBOL");
    FormElementType SYMBOLS_DECLARATION = new FormElementType("SYMBOLS_DECLARATION");

    FormElementType BINARY_EXPRESSION = new FormElementType("BINARY_EXPRESSION");
    FormElementType PARENTHESIZED = new FormElementType("PARENTHESIZED_EXPRESSION");
    FormElementType REFERENCE_EXPRESSION = new FormElementType("FORM_REFERENCE_EXPRESSION");
    FormElementType OPERATION_REFERENCE = new FormElementType("OPERATION_REFERENCE_EXPRESSION");
    FormElementType INTEGER_CONSTANT   = new FormElementType("INTEGER_CONSTANT");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == SYMBOL) {
                return new FormSymbolImpl(node);
            } else if (type == SYMBOLS_DECLARATION) {
                return new FormSymbolsDeclarationStatementImpl(node);
            } else if (type == BINARY_EXPRESSION) {
                return new FormBinaryExpressionImpl(node);
            } else if (type == REFERENCE_EXPRESSION){
                return new FormReferenceExpressionImpl(node);
            } else if(type == INTEGER_CONSTANT){
                return new FormConstantExpression(node);
            } else if(type == OPERATION_REFERENCE){
                return new FormOperationReferenceExpression(node);
            } else if(type == PARENTHESIZED) {
                return new FormParenthesizedExpression(node);
            } else if(type == PRINT_STATEMENT) {
                return new FormPrintStatement(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
