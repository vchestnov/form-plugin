package com.form.parser;

import com.form.psi.FormElementType;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import static com.form.lexer.FormTokens.*;
import static com.form.psi.FormElementTypes.*;

public class FormExpressionParsing extends AbstractFormParsing {
    static final TokenSet EXPRESSION_FIRST = TokenSet.create(
            LPAR, // parenthesized

            // literal constant
            INTEGER_LITERAL,

            IDENTIFIER // SimpleName
    );

    public enum Precedence {

        MULTIPLICATIVE(MUL, DIV){
            @Override
            public void parseHigherPrecedence(FormExpressionParsing parser) {
                parser.parseAtomicExpression();
            }
        },
        ADDITIVE(PLUS, MINUS),
        ASSIGNMENT(EQ);

        static {
            Precedence[] values = Precedence.values();
            for (Precedence precedence : values) {
                int ordinal = precedence.ordinal();
                precedence.higher = ordinal > 0 ? values[ordinal - 1] : null;
            }
        }

        private Precedence higher;
        private final TokenSet operations;

        Precedence(IElementType... operations) {
            this.operations = TokenSet.create(operations);
        }

        public void parseHigherPrecedence(FormExpressionParsing parser) {
            assert higher != null;
            parser.parseBinaryExpression(higher);
        }

        /**
         *
         * @param operation the operation sign (e.g. PLUS or IS)
         * @param parser the parser object
         * @return node type of the result
         */
        public FormElementType parseRightHandSide(IElementType operation, FormExpressionParsing parser) {
            parseHigherPrecedence(parser);
            return BINARY_EXPRESSION;
        }

        @NotNull
        public final TokenSet getOperations() {
            return operations;
        }
    }

    public FormExpressionParsing(PsiBuilder builder) {
        super(builder);
    }

    public void parseExpression() {
        if (!atSet(EXPRESSION_FIRST)) {
            error("Expecting an expression");
            return;
        }
        parseBinaryExpression(Precedence.ASSIGNMENT);
    }

    private void parseAtomicExpression() {
        if (at(IDENTIFIER)) {
            parseSimpleNameExpression();
        }
    }


    public void parseSimpleNameExpression() {
        PsiBuilder.Marker simpleName = mark();
        expect(IDENTIFIER, "Expecting an identifier");
        simpleName.done(REFERENCE_EXPRESSION);
    }

    private void parseOperationReference() {
        PsiBuilder.Marker operationReference = mark();
        advance(); // operation
        operationReference.done(OPERATION_REFERENCE);
    }

    public void parseBinaryExpression(Precedence precedence) {
        PsiBuilder.Marker expression = mark();

        precedence.parseHigherPrecedence(this);

        while (!at(SEMICOLON) && atSet(precedence.getOperations())) {
            IElementType operation = tt();

            parseOperationReference();

            FormElementType resultType = precedence.parseRightHandSide(operation, this);
            expression.done(resultType);
            expression = expression.precede();
        }

        expression.drop();
    }

}
