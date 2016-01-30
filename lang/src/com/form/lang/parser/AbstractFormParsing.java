package com.form.lang.parser;

import com.form.lang.lexer.FormToken;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

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

    /**
     * Side-effect-free version of atSet()
     */
    protected boolean _atSet(IElementType... tokens) {
        return _atSet(TokenSet.create(tokens));
    }

    /**
     * Side-effect-free version of atSet()
     */
    protected boolean _atSet(TokenSet set) {
        IElementType token = tt();
        if (set.contains(token)) return true;
        return false;
    }

    protected boolean atSet(IElementType... tokens) {
        return atSet(TokenSet.create(tokens));
    }

    protected boolean atSet(TokenSet set) {
        if (_atSet(set)) return true;
//        IElementType token = tt();
//        if (token == IDENTIFIER) {
//            KtKeywordToken keywordToken = SOFT_KEYWORD_TEXTS.get(myBuilder.getTokenText());
//            if (keywordToken != null && set.contains(keywordToken)) {
//                myBuilder.remapCurrentToken(keywordToken);
//                return true;
//            }
//        }
//        else {
//            // We know at this point that <code>set</code> does not contain <code>token</code>
//            if (set.contains(IDENTIFIER) && token instanceof KtKeywordToken) {
//                if (((KtKeywordToken) token).isSoft()) {
//                    myBuilder.remapCurrentToken(IDENTIFIER);
//                    return true;
//                }
//            }
//        }
        return false;
    }

    protected void error(String message) {
        builder.error(message);
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

    protected boolean expect(FormToken expectation, String message) {
        if (at(expectation)) {
            advance(); // expectation
            return true;
        }

//        if (expectation == KtTokens.IDENTIFIER && "`".equals(myBuilder.getTokenText())) {
//            advance();
//        }

        error(message);
        return false;
    }
}
