package com.form.lang.parsing;

import com.form.test.FormTestDataFixture;

@SuppressWarnings("all")
public class FormParsingTest extends FormBaseParsingTestCase {
    public void testKeywordsTest() throws Exception {
        doTest(true);
    }

    public void testIfTest() throws Exception {
        doTest(true);
    }

    public void testMacrosTest() throws Exception {
        doTest(true);
    }
}