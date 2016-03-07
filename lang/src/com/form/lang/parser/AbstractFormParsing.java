package com.form.lang.parser;

import com.form.lang.lexer.FormToken;
import com.form.lang.lexer.FormTokens;
import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static com.form.lang.FormNodeTypes.*;
import static com.form.lang.lexer.FormTokens.*;

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

    protected PsiBuilder.Marker mark() {
        return builder.mark();
    }

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

    protected void _advance() {
        builder.advanceLexer();
    }

    protected void advance() {
        ProgressManager.checkCanceled();

        IElementType token = builder.getTokenType();
        if (FormTokens.DIRECTIVES.contains(token)) {
            parseDirective();
        }

        builder.advanceLexer();
    }

    protected void parseDirective() {
        IElementType headerToken = builder.getTokenType();

        assert DIRECTIVES.contains(headerToken);
        final PsiBuilder.Marker marker = mark();
        _advance();
        if (headerToken == IFDEF_DIRECTIVE || headerToken == IFNDEF_DIRECTIVE) {
            parseMacroReference();
            while (!eof() && !isEndOfDirective(tt())) {
                _advance();
            }
            _advance();
            marker.done(DIRECTIVE);
        } else if (headerToken == DEFINE_DIRECTIVE ||
                headerToken == REDEFINE_DIRECTIVE ||
                headerToken == UNDEFINE_DIRECTIVE) {

            final IElementType tokenType = tt();
            if (headerToken == UNDEFINE_DIRECTIVE) {
                if (tokenType == IDENTIFIER) {
                    PsiBuilder.Marker ref = mark();
                    _advance();
                    ref.done(MACRO_REFERENCE);
                } else {
                    error("Macro name expected");
                }
            } else if (_at(IDENTIFIER)) {
                _advance();

                if(_at(LPAR)) {
                    final PsiBuilder.Marker paramList = mark();
                    _advance();
                    while (!eof() && tt() != RPAR) {
                        if (tt() == IDENTIFIER) {
                            PsiBuilder.Marker param = mark();
                            _advance();
                            param.done(MACRO_PARAMETER);
                        } else {
                            error("Expecting macro parameter");
                            if (tt() != COMMA) _advance();
                        }

                        if (tt() == COMMA) {
                            _advance();
                        } else if (tt() != RPAR) {
                            error("',' or ')' expected");
                            break;
                        }
                    }
                    expect(RPAR, "')' missing");
                    paramList.done(MACRO_PARAMETER_LIST);
                }
                parseStringLiteral();
            }

            expect(END_OF_DIRECTIVE_CONTENT, "End of directive expected");
            if (headerToken == DEFINE_DIRECTIVE) {
                marker.done(MACRO_DEFINITION);
            } else if (headerToken == REDEFINE_DIRECTIVE) {
                marker.done(MACRO_REDEFINITION);
            } else {
                marker.done(UNDEFINE_DIRECTIVE);
            }
        } else {
            while (!eof() && !isEndOfDirective(tt())) {
                _advance();
            }
            _advance();
            marker.done(DIRECTIVE);
        }

    }

    private void parseStringLiteral() {
        PsiBuilder.Marker string = mark();
        if (at(OPEN_QUOTE)) {
            _advance();
        } else {
            error("\" expected");
            string.drop();
            return;
        }

        while (!eof() && !_at(CLOSING_QUOTE)) {
            if (atSet(REGULAR_STRING_PART, MACRO_REFERENCE)) {
                _advance();
            } else {
                errorAndAdvance("Unexpected element");
            }
        }

        if (at(CLOSING_QUOTE)) {
            _advance();
        }
        string.done(STRING_LITERAL);
    }

    private static boolean isEndOfDirective(IElementType type) {
        return type == END_OF_DIRECTIVE_CONTENT || type == DIRECTIVE_CONTENT;
    }

    protected void parseMacroReference() {
        PsiBuilder.Marker marker = mark();
        if (at(BACKQUOTE)) {
            _advance();
            if (at(TYLDA)) {
                _advance();
            }

            expect(IDENTIFIER, "Macro name expected");
            expect(QUOTE, "' expected");
            marker.done(MACRO_REFERENCE);
        } else {
            error("Macro reference expected");
            marker.drop();
        }
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
