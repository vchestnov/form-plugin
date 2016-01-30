package com.form.lang.parsing;

import com.form.lang.parser.FormParserDefinition;
import com.intellij.lang.ParserDefinition;
import com.intellij.testFramework.ParsingTestCase;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AbstractParsingTest extends ParsingTestCase {
    protected AbstractParsingTest(@NonNls @NotNull String dataPath, @NotNull String fileExt, @NotNull ParserDefinition... definitions) {
        super(".", "frm", new FormParserDefinition());
    }
}
