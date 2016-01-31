package com.form.lang.parser;

import com.form.lang.psi.FormElementTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static com.form.lang.lexer.FormTokens.*;
import static com.form.lang.psi.FormElementTypes.*;

public class FormParsing extends AbstractFormParsing {
    private FormExpressionParsing expressionParsing;

    TokenSet types = TokenSet.create(SYMBOLS_KEYWORD, VECTORS_KEYWORD, FUNCTIONS_KEYWORD, CFUNCTIONS_KEYWORD,
            TENSORS_KEYWORD, CTENSORS_KEYWORD, NTENSORS_KEYWORD, INDICES_KEYWORD);

    public FormParsing(PsiBuilder builder) {
        super(builder);
        expressionParsing = new FormExpressionParsing(builder);
    }

    public void parseFile(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = mark();
        while (!eof()) {
            parseStatement();
        }
        rootMarker.done(FormElementTypes.FORM_FILE);
    }

    private void parseStatement() {
        IElementType keywordToken = tt();
        if (atSet(types)) {
            parseDeclarationStatement();
        } else if (atSet(MODULE_INSTRUCTIONS)) {
            parseModuleInstruction();
        } else if (keywordToken == LOCAL_KEYWORD) {
            parseLocalDeclarationStatement();
        } else if (keywordToken == ID_KEYWORD) {
            parseIdentifyStatement();
        } else if (keywordToken == PRINT_KEYWORD) {
            parsePrintStatement();
        } else {
            errorAndAdvance("Expecting a statement");
        }
    }

    private void parsePrintStatement() {
        assert at(PRINT_KEYWORD);
        PsiBuilder.Marker print = mark();
        advance();
        while (true) {
            if (at(COMMA)) {
                errorAndAdvance("Expecting a parameter declaration");
            }

            expressionParsing.parseSimpleNameExpression();

            if (at(COMMA)) {
                advance();
            } else if (at(SEMICOLON)) {
                advance();
                break;
            } else {
                if (!at(RPAR)) error("Expecting comma or ';'");
            }
        }
        print.done(PRINT_STATEMENT);
    }

    private void parseLocalDeclarationStatement() {
        assert at(LOCAL_KEYWORD);
        PsiBuilder.Marker localStatement = mark();
        advance();

        PsiBuilder.Marker access = builder.mark();
        if (!at(IDENTIFIER)) {
            error("Expecting an identifier");
            return;
        }
        advance();
        if(expressionParsing.parseCallOrAccessSuffix()){
            access.done(CALL_OR_ACCESS_EXPRESSION);
        } else {
            access.drop();
        }

        if (!at(EQ)) {
            error("Expression must be declared");
            return;
        }
        advance();

        expressionParsing.parseExpression();

        expect(SEMICOLON, "';' expected");
        localStatement.done(LOCAL_STATEMENT);
    }

    private void parseIdentifyStatement() {
        assert at(ID_KEYWORD);
        PsiBuilder.Marker identifyStatement = mark();
        advance();

        PsiBuilder.Marker access = builder.mark();
        if (!at(IDENTIFIER)) {
            error("Expecting an identifier");
            return;
        }
        advance();
        if(expressionParsing.parseCallOrAccessSuffix()){
            access.done(CALL_OR_ACCESS_EXPRESSION);
        } else {
            access.drop();
        }

        if (!at(EQ)) {
            error("Expression must be declared");
            return;
        }
        advance();

        expressionParsing.parseExpression();

        expect(SEMICOLON, "';' expected");
        identifyStatement.done(IDENTIFY_STATEMENT);
    }

    private void parseModuleInstruction() {
        assert atSet(MODULE_INSTRUCTIONS);
        PsiBuilder.Marker instruction = mark();
        advance();
        instruction.done(MODULE_INSTRUCTION);
        if(at(SEMICOLON)) advance();
    }

    private void parseDeclarationStatement() {
        assert atSet(types);

        PsiBuilder.Marker symbolsDeclaration = mark();
        do {
            builder.advanceLexer();
            parseSymbol();
        } while (builder.getTokenType() == COMMA);

        if (builder.getTokenType() == SEMICOLON) {
            builder.advanceLexer();
        } else {
            builder.advanceLexer();
            builder.error("';' expected");
        }
        symbolsDeclaration.done(FormElementTypes.DECLARATION_STATEMENT);
    }

    private void parseSymbol() {
        if (at(IDENTIFIER)) {
            final PsiBuilder.Marker symbolIdentifier = mark();
            advance();
            symbolIdentifier.done(DECLARATION);
        } else {
            error("Symbol name expected");
        }
    }
}
