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
//        doTest(true);
        String name = this.getTestName();
        String e = this.loadFile(name + "." + this.myFileExt);
        this.myFile = this.createPsiFile(name, e);
        this.checkResult(name, this.myFile);
    }
}