package com.form.lang.lexer;

import com.intellij.lexer.LayeredLexer;
import org.jetbrains.annotations.NotNull;

public class FormHighlightingLexer extends LayeredLexer {
    public FormHighlightingLexer() {
        super(new FormLexer());

        registerLayer(new MyDirectiveLexer(), FormTokens.DIRECTIVE_CONTENT);
    }

    private static class MyDirectiveLexer extends FormLexer {
        public MyDirectiveLexer() {
            super();
        }

        @Override
        public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
            super.start(buffer, startOffset, endOffset, FormLexer.INITIAL);
        }
    }
}
