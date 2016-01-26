package com.form.parser;

import com.form.psi.FormElementTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

import static com.form.lexer.FormTokens.*;
import static com.form.psi.FormElementTypes.*;

public class FormParsing extends AbstractFormParsing {
    public FormParsing(PsiBuilder builder) {
        super(builder);
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
        } else if (keywordToken == END_KEYWORD) {
            parseEndOfFileStatement();
        } else {
            errorAndAdvance("Expecting a statement");
        }
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
            builder.error("Semicolon expected");
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
