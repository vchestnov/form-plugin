package com.form.lang.parsing;

import com.form.test.FormTestDataFixture;

@SuppressWarnings("all")
public class FormParsingTest extends FormBaseParsingTestCase {
//    public static class Psi extends FormBaseParsingTestCase {
        public void testAbsentInnerType() throws Exception {
            String fileName = FormTestDataFixture.navigationMetadata("FirstTest.frm");
            doParsingTest(fileName);
//          TODO: use this method from ParsingTestCase
//            doTest(true);
        }
//    }
    public void testFirstTest() throws Exception {
        doTest(true);
    }
}