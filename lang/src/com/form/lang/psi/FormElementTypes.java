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
    FormElementType IDENTIFY_STATEMENT = new FormElementType("IDENTIFY_STATEMENT");
    FormElementType LOCAL_STATEMENT = new FormElementType("LOCAL_STATEMENT");
    FormElementType DECLARATION_STATEMENT = new FormElementType("DECLARATION_STATEMENT");
    FormElementType MODULE_INSTRUCTION = new FormElementType("MODULE_INSTRUCTION");
    FormElementType DECLARATION = new FormElementType("DECLARATION");

    FormElementType BINARY_EXPRESSION = new FormElementType("BINARY_EXPRESSION");
    FormElementType CALL_OR_ACCESS_EXPRESSION = new FormElementType("CALL_OR_ACCESS_EXPRESSION");
    FormElementType PARENTHESIZED = new FormElementType("PARENTHESIZED_EXPRESSION");
    FormElementType REFERENCE_EXPRESSION = new FormElementType("REFERENCE_EXPRESSION");
    FormElementType ARGUMENT_LIST = new FormElementType("ARGUMENT_LIST");
    FormElementType OPERATION_REFERENCE = new FormElementType("OPERATION_REFERENCE_EXPRESSION");
    FormElementType DUMMY_INDEX = new FormElementType("DUMMY_INDEX");
    FormElementType INTEGER_CONSTANT = new FormElementType("INTEGER_CONSTANT");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == DECLARATION) {
                return new FormDeclaration(node);
            } else if (type == DECLARATION_STATEMENT) {
                return new FormDeclarationStatement(node);
            } else if (type == BINARY_EXPRESSION) {
                return new FormBinaryExpressionImpl(node);
            } else if (type == REFERENCE_EXPRESSION) {
                return new FormReferenceExpressionImpl(node);
            } else if (type == INTEGER_CONSTANT) {
                return new FormConstantExpression(node);
            } else if (type == OPERATION_REFERENCE) {
                return new FormOperationReferenceExpression(node);
            } else if (type == PARENTHESIZED) {
                return new FormParenthesizedExpression(node);
            } else if (type == PRINT_STATEMENT) {
                return new FormPrintStatement(node);
            } else if (type == CALL_OR_ACCESS_EXPRESSION) {
                return new FormCallOrAccessExpression(node);
            } else if (type == ARGUMENT_LIST) {
                return new FormArgumentsList(node);
            } else if (type == DUMMY_INDEX) {
                return new FormDummyIndex(node);
            } else if (type == IDENTIFY_STATEMENT) {
                return new FormIdentifyStatement(node);
            } else if (type == LOCAL_STATEMENT) {
                return new FormLocalStatement(node);
            } else if (type == MODULE_INSTRUCTION) {
                return new FormModuleInstruction(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
