// This is a generated file. Not intended for manual editing.
package com.form.lang;

import com.form.idea.FormLanguage;
import com.form.lang.psi.impl.*;
import com.intellij.psi.tree.IFileElementType;

public interface FormNodeTypes {
    IFileElementType FORM_FILE = new IFileElementType(FormLanguage.INSTANCE);

    FormNodeType PRINT_STATEMENT = new FormNodeType("PRINT", FormPrintStatement.class);
    FormNodeType IDENTIFY_STATEMENT = new FormNodeType("IDENTIFY_STATEMENT", FormIdentifyStatement.class);
    FormNodeType LOCAL_STATEMENT = new FormNodeType("LOCAL_STATEMENT", FormLocalStatement.class);
    FormNodeType DECLARATION_STATEMENT = new FormNodeType("DECLARATION_STATEMENT", FormDeclarationStatement.class);
    FormNodeType MODULE_INSTRUCTION = new FormNodeType("MODULE_INSTRUCTION", FormModuleInstruction.class);
    FormNodeType DECLARATION = new FormNodeType("DECLARATION", FormDeclaration.class);

    FormNodeType IF = new FormNodeType("IF", FormElementImpl.class);
    FormNodeType CONDITION = new FormNodeType("CONDITION", FormElementImpl.class);
    FormNodeType IF_BRANCH = new FormNodeType("IF_BRANCH", FormElementImpl.class);

    FormNodeType DIRECTIVE = new FormNodeType("DIRECTIVE", FormElementImpl.class);
    FormNodeType MACRO_DEFINITION = new FormNodeType("MACRO_DEFINITION", FormElementImpl.class);
    FormNodeType MACRO_REDEFINITION = new FormNodeType("MACRO_REDEFINITION", FormElementImpl.class);
    FormNodeType MACRO_UNDEFINITION = new FormNodeType("MACRO_UNDEFINITION", FormElementImpl.class);
    FormNodeType STRING_LITERAL = new FormNodeType("STRING_LITERAL", FormElementImpl.class);

    FormNodeType BINARY_EXPRESSION = new FormNodeType("BINARY_EXPRESSION", FormBinaryExpressionImpl.class);
    FormNodeType CALL_OR_ACCESS_EXPRESSION = new FormNodeType("CALL_OR_ACCESS_EXPRESSION", FormCallOrAccessExpression.class);
    FormNodeType PARENTHESIZED = new FormNodeType("PARENTHESIZED_EXPRESSION", FormParenthesizedExpression.class);
    FormNodeType REFERENCE_EXPRESSION = new FormNodeType("REFERENCE_EXPRESSION", FormReferenceExpressionImpl.class);
    FormNodeType ARGUMENT_LIST = new FormNodeType("ARGUMENT_LIST", FormArgumentsList.class);
    FormNodeType OPERATION_REFERENCE = new FormNodeType("OPERATION_REFERENCE_EXPRESSION", FormOperationReferenceExpression.class);
    FormNodeType DUMMY_INDEX = new FormNodeType("DUMMY_INDEX", FormDummyIndex.class);
    FormNodeType INTEGER_CONSTANT = new FormNodeType("INTEGER_CONSTANT", FormConstantExpression.class);

}
