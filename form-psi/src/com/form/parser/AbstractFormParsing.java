package com.form.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

public class AbstractFormParsing {
    protected final PsiBuilder builder;

    public AbstractFormParsing(PsiBuilder builder) {
        this.builder = builder;
    }

    protected IElementType tt() {
        return builder.getTokenType();
    }

    protected boolean eof() {
        return builder.eof();
    }

    protected PsiBuilder.Marker mark() { return builder.mark(); }

    protected boolean _at(IElementType expectation) {
        IElementType token = tt();
        return tokenMatches(token, expectation);
    }

    protected boolean at(IElementType expectation) {
        if (_at(expectation)) return true;
//        IElementType token = tt();
//        if (token == IDENTIFIER && expectation instanceof KtKeywordToken) {
//            KtKeywordToken expectedKeyword = (KtKeywordToken) expectation;
//            if (expectedKeyword.isSoft() && expectedKeyword.getValue().equals(myBuilder.getTokenText())) {
//                myBuilder.remapCurrentToken(expectation);
//                return true;
//            }
//        }
//        if (expectation == IDENTIFIER && token instanceof KtKeywordToken) {
//            KtKeywordToken keywordToken = (KtKeywordToken) token;
//            if (keywordToken.isSoft()) {
//                myBuilder.remapCurrentToken(IDENTIFIER);
//                return true;
//            }
//        }
        return false;
    }

    protected boolean errorAndAdvance(String message) {
        return errorAndAdvance(message, 1);
    }

    protected boolean errorAndAdvance(String message, int advanceTokenCount) {
        PsiBuilder.Marker err = mark();
        advance(advanceTokenCount);
        err.error(message);
        return false;
    }

    protected void advance() {
        builder.advanceLexer();
    }

    protected void advance(int advanceTokenCount) {
        for (int i = 0; i < advanceTokenCount; i++) {
            advance(); // erroneous token
        }
    }

    private boolean tokenMatches(IElementType token, IElementType expectation) {
        return token == expectation;
    }
}
