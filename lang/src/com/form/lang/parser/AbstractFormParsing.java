package com.form.lang.parser;

import com.form.lang.lexer.FormToken;
import com.form.lang.lexer.FormTokens;
import com.form.lang.preprocessor.FormMacroReferenceTokenType;
import com.form.lang.util.FormElementUtilsKt;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nullable;

import static com.form.lang.FormNodeTypes.*;
import static com.form.lang.lexer.FormTokens.*;

public class AbstractFormParsing {
    protected final PsiBuilder builder;

    public AbstractFormParsing(PsiBuilder builder) {
        this.builder = builder;
    }

    protected boolean eof() {
        return builder.eof();
    }

    protected PsiBuilder.Marker mark() {
        return builder.mark();
    }

    protected boolean _at(IElementType expectation) {
        IElementType token = _tt();
        return tokenMatches(token, expectation);
    }

    protected boolean at(IElementType expectation) {
        IElementType token = tt();
        return tokenMatches(token, expectation);
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
        IElementType token = _tt();
        if (set.contains(token)) return true;
        return false;
    }

    protected boolean atSet(IElementType... tokens) {
        return atSet(TokenSet.create(tokens));
    }

    protected boolean atSet(TokenSet set) {
        IElementType token = tt();
        if (set.contains(token)) return true;
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
        builder.advanceLexer();
    }

    protected IElementType _tt() {
        IElementType base;
        while(true) {
            base = FormElementUtilsKt.getUnwrappedTokeType(builder.getTokenType());

            if (WHITE_SPACES.contains(base))
                builder.advanceLexer();
            else
                break;
        }
        return base;
    }

    protected IElementType tt() {
        IElementType token = builder.getTokenType();
        if (FormTokens.DIRECTIVES.contains(token)) {
            parseDirective();
        } else if (token instanceof FormMacroReferenceTokenType) {
//            FormMacroReferenceTokenType macroToken = (FormMacroReferenceTokenType) token;

//            if (!myIsInsideMacro) {
//                myIsInsideMacro = true;
            parseMacroCall();
//                myIsInsideMacro = false;
//            } else {
//                final IElementType delegate = macroToken.getDelegate();
//                if (WHITESPACES.contains(delegate) || COMMENTS.contains(delegate) || delegate == EOL_ESCAPE)
//                    baseAdvance();
//                else
//                    return delegate;
//            }
        }
        return _tt();
    }

    @Nullable
    private FormMacroReferenceTokenType asMacroToken() {
        IElementType token = FormElementUtilsKt.getUnwrappedTokeType(_tt());
        return token instanceof FormMacroReferenceTokenType ? (FormMacroReferenceTokenType) token : null;
    }


    private void parseMacroCall() {
        FormMacroReferenceTokenType token = asMacroToken();
        if (token == null || token.getDelegate() != BACKQUOTE) return;

        PsiBuilder.Marker macroCall = mark();
        _advance();

        FormMacroReferenceTokenType rootToken = asMacroToken();
        if (rootToken == null) {
            macroCall.done(MACRO_CALL);
            return;
        }
        IElementType rootTokenDelegate = rootToken.getDelegate();
        if((rootTokenDelegate != IDENTIFIER && !KEYWORDS.contains(rootTokenDelegate)) || !rootToken.isRoot()){
            builder.error("Macro name expected");
            macroCall.done(MACRO_CALL);
            return;
        }
        PsiBuilder.Marker ref = mark();
        _advance();
        ref.done(MACRO_REFERENCE);
//        FormMacroReferenceTokenType token = asMacroToken();
//
//        if ((delegate != IDENTIFIER && !KEYWORDS.contains(delegate)) || !token.isRoot())
//            return false;
//
//        myDisableErrors = true;
//
//        int level = token.getMacroLevel();
//
//        PsiBuilder.Marker macroCall = mark();
//
//        PsiBuilder.Marker ref = mark();
//        baseAdvance();
//        ref.done(MACRO_REF);
//
//        token = asMacroToken();
//        PsiBuilder.Marker param = null;
//
//        while (token != null) {
//            if (token.getMacroLevel() < level || token.isRoot()) break;
//
//            if (true /*myBuilder.getTokenType() instanceof OCMacroReferenceTokenType*/) { // Foreign tokens in recursive params won't be handled
//                if (token.isParamToken() && param == null) {
//                    param = mark();
//                    int offset = myBuilder.getCurrentOffset();
//                    boolean wasStatement = false;
//
//                    while (tryParseStatement() && myBuilder.getCurrentOffset() > offset) {
//                        offset = myBuilder.getCurrentOffset();
//                        wasStatement = true;
//                    }
//
//                    if (wasStatement || detectTypeName() && parseTypeName(DeclarationContext.PARAMETER_LIST, TypeParsingExpectation.ANY) || parseAssignmentExpression(false)) {
//                        OCMacroReferenceTokenType nextToken = asMacroToken();
//
//                        if (nextToken != null) {
//                            token = nextToken;
//                            continue;
//                        }
//                    }
//
//                    rollbackTo(param);
//                    param = mark();
//                } else if (!token.isParamToken()) {
//                    IElementType tokenType = token.getDelegate();
//
//                    if ((tokenType == COMMA || tokenType == RPAR) && param == null)
//                        param = mark();
//                    if (param != null) {
//                        param.done(MACRO_ARGUMENT);
//                        param = null;
//                    }
//                }
//            }
//
//            if (token.getMacroLevel() > level) {
//                if (!parseMacroCall()) baseAdvance();
//            } else {
//                baseAdvance();
//            }
//
//            token = asMacroToken();
//        }
//
//        if (param != null)
//            param.done(MACRO_ARGUMENT);
//
//        if (token == null) {
//            IElementType foreignToken = baseToken();
//
//            while (foreignToken instanceof OCMacroReferenceTokenType) {
//                if (((OCMacroReferenceTokenType) foreignToken).getMacroLevel() != level) break;
//
//                baseAdvance();
//                foreignToken = baseToken();
//            }
//        }
//
        FormMacroReferenceTokenType closingQuote = asMacroToken();
        if (closingQuote == null || closingQuote.getDelegate() != QUOTE) {
            builder.error("' expected");
            macroCall.done(MACRO_CALL);
            return;
        }
        _advance();
        macroCall.done(MACRO_CALL);
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

                if (_at(LPAR)) {
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
            while (!eof() && !isEndOfDirective(tt())) {
                if(WHITE_SPACES.contains(tt())) {
                    _advance();
                } else {
                    errorAndAdvance("Unexpected element");
                }
            }
            _advance();
            if (headerToken == DEFINE_DIRECTIVE) {
                marker.done(MACRO_DEFINITION);
            } else if (headerToken == REDEFINE_DIRECTIVE) {
                marker.done(MACRO_REDEFINITION);
            } else {
                marker.done(MACRO_UNDEFINITION);
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
        if (at(OPENING_QUOTE)) {
            _advance();
        } else {
            error("\" expected");
            string.drop();
            return;
        }

        while (!eof() && !_at(CLOSING_QUOTE)) {
            IElementType token = builder.getTokenType();
            if ( token instanceof FormMacroReferenceTokenType || token == REGULAR_STRING_PART) {
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
