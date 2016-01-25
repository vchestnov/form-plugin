package com.form.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LightPlatformTestCase;
import org.jetbrains.annotations.NonNls;

public class FormLexerTests extends LightPlatformTestCase {
    private static void doTest(@NonNls String text, @NonNls String[] expectedTokens) {
        Lexer lexer = new FormLexer();
        doTest(text, expectedTokens, lexer);
    }

    private static void doTestHL(@NonNls String text, @NonNls String[] expectedTokens) {
        Lexer lexer = new FormHighlightingLexer();
        doTest(text, expectedTokens, lexer);
    }

    private static void doTest(String text, String[] expectedTokens,Lexer lexer) {
        lexer.start(text);
        int idx = 0;
        while (lexer.getTokenType() != null) {
            if (idx >= expectedTokens.length) fail("Too many tokens");
            String tokenName = lexer.getTokenType().toString();
            String expectedTokenType = expectedTokens[idx++];
            String expectedTokenText = expectedTokens[idx++];
            assertEquals(expectedTokenType, tokenName);
            String tokenText = lexer.getBufferSequence().subSequence(lexer.getTokenStart(), lexer.getTokenEnd()).toString();
            assertEquals(expectedTokenText, tokenText);
            lexer.advance();
        }

        if (idx < expectedTokens.length) fail("Not enough tokens");
    }
}
