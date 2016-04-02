package com.form.test;

import com.form.util.PathUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.TestDataFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FormTestDataFixture {
    public static String navigationMetadata(@TestDataFile String testFile) {
        return testFile;
    }

    public static String getHomeDirectory() {
//        TODO: change this
        File resourceRoot = PathUtil.getResourcePathForClass(FormTestDataFixture.class);
        File f1 = resourceRoot.getParentFile();
        File f2 = f1.getParentFile();
        String f3 = f2.getParent();
        return FileUtil.toSystemIndependentName(f3);
    }

    @NotNull
    public static File getLangTestData() { return new File(getHomeDirectory(), "lang-tests/testData"); }
}
