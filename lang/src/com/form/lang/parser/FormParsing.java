package com.form.lang.parser;

import com.form.lang.psi.FormElementTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

import static com.form.lang.lexer.FormTokens.*;
import static com.form.lang.psi.FormElementTypes.*;

public class FormParsing extends AbstractFormParsing {
    private FormExpressionParsing expressionParsing;

    public FormParsing(PsiBuilder builder) {
        super(builder);
        expressionParsing = new FormExpressionParsing(builder);
    }

    public void parseFile(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = mark();
        while (!eof()) {
            parseDeclaration();
        }
        rootMarker.done(FormElementTypes.FORM_FILE);
    }

    private void parseDeclaration() {
        IElementType keywordToken = tt();
        if (keywordToken == SYMBOLS_KEYWORD) {
            parseSymbolsDeclarationStatement();
        } else if (keywordToken == LOCAL_KEYWORD) {
            parseLocalDeclarationStatement();
        } else if (keywordToken == ID_KEYWORD) {
            parseIdentifyStatement();
        } else if (keywordToken == END_KEYWORD) {
            parseEndOfFileStatement();
        } else if (keywordToken == PRINT_KEYWORD) {
            parsePrintStatement();
        } else {
            errorAndAdvance("Expecting a statement");
        }
    }

    private void parseIdentifyStatement() {
        assert at(ID_KEYWORD);
        advance();

        if (!at(IDENTIFIER)) {
            error("Expecting an identifier");
            return;
        }
        advance();

        if (!at(EQ)) {
            error("Expression must be declared");
            return;
        }
        advance();

        expressionParsing.parseExpression();

        expect(SEMICOLON, "';' expected");
    }

    private void parsePrintStatement() {
        assert at(PRINT_KEYWORD);
        PsiBuilder.Marker print = mark();
        advance();
        expect(SEMICOLON, "';' expected");
        print.done(PRINT_STATEMENT);
    }

    private void parseLocalDeclarationStatement() {
        assert at(LOCAL_KEYWORD);
        advance();

        if (!at(IDENTIFIER)) {
            error("Expecting an identifier");
            return;
        }
        advance();

        if (!at(EQ)) {
            error("Expression must be declared");
            return;
        }
        advance();

        expressionParsing.parseExpression();

        expect(SEMICOLON, "';' expected");
    }

    private void parseEndOfFileStatement() {
        assert at(END_KEYWORD);
        advance();
        if (!eof()) {
            PsiBuilder.Marker error = mark();
            while (!eof()) advance();
            error.error("Statements are not allowed after end statement");
        }
    }

    private void parseSymbolsDeclarationStatement() {
        assert at(SYMBOLS_KEYWORD);

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
        symbolsDeclaration.done(FormElementTypes.SYMBOLS_DECLARATION);
    }

    private void parseSymbol() {
        if (builder.getTokenType() == IDENTIFIER) {
            final PsiBuilder.Marker symbolIdentifier = builder.mark();
            builder.advanceLexer();
            symbolIdentifier.done(SYMBOL);
        } else {
            builder.advanceLexer();
            builder.error("Symbol name expected");
        }
    }
}
