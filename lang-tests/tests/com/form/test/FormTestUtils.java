package com.form.test;

import com.form.util.PathUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.TestDataFile;

import java.io.File;

public class FormTestUtils {
    public static String navigationMetadata(@TestDataFile String testFile) {
        return testFile;
    }

    public static String getHomeDirectory() {
        File resourceRoot = PathUtil.getResourcePathForClass(FormTestUtils.class);
        return FileUtil.toSystemIndependentName(resourceRoot.getParentFile().getParentFile().getParent());
    }
}
