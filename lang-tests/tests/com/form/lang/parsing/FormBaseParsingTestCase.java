package com.form.lang.parsing;

import com.form.lang.parser.FormParserDefinition;
import com.form.lang.FormNodeTypes;
import com.form.test.FormTestDataFixture;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.ParsingTestCase;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

public abstract class FormBaseParsingTestCase extends ParsingTestCase {
    @Override
    protected String getTestDataPath() {
//        TODO: this is the correct way of doing things
        return FormTestDataFixture.getLangTestData() + "/psi";
//        TODO: this is not cool
//        return FormTestDataFixture.getHomeDirectory();
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    protected FormBaseParsingTestCase() {
        super(".", "frm", new FormParserDefinition());
    }

    protected void doParsingTest(@NotNull String filePath) throws Exception {
        doBaseTest(filePath, FormNodeTypes.FORM_FILE);
    }

    private void doBaseTest(@NotNull String filePath, @NotNull IElementType fileType) throws Exception {
        myFileExt = FileUtilRt.getExtension(PathUtil.getFileName(filePath));
        myFile = createPsiFile(FileUtil.getNameWithoutExtension(PathUtil.getFileName(filePath)), loadFile(filePath));
        doCheckResult(myFullDataPath, filePath.replaceAll("\\.frm", ".txt"), toParseTreeText(myFile, false, false).trim());
    }
}
