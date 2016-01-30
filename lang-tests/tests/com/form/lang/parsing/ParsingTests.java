package com.form.lang.parsing;

import com.form.test.FormTestUtils;

@SuppressWarnings("all")
public class ParsingTests extends AbstractParsingTest {
    public static class Psi extends AbstractParsingTest {
        public void testAbsentInnerType() throws Exception {
            String fileName = FormTestUtils.navigationMetadata("lang-tests/testData/psi/FirstTest.frm");
            doParsingTest(fileName);
        }
    }
}