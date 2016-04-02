package com.form.lang.parsing;

import com.form.test.FormTestDataFixture;

@SuppressWarnings("all")
public class FormParsingTest extends FormBaseParsingTestCase {
//    public static class Psi extends FormBaseParsingTestCase {
        public void testAbsentInnerType() throws Exception {
            String fileName = FormTestDataFixture.navigationMetadata("SimpleTest.frm");
            doParsingTest(fileName);
//          TODO: use this method from ParsingTestCase
//            doTest(true);
        }
//    }
    public void testSimpleTest() throws Exception {
        doTest(true);
    }

    public void testMacrosTest() throws Exception {
        doTest(true);
    }
}