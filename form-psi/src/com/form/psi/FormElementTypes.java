// This is a generated file. Not intended for manual editing.
package com.form.psi;

import com.form.plugin.FormLanguage;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.form.psi.impl.*;
import com.intellij.psi.tree.IFileElementType;

public interface FormElementTypes {
  IFileElementType FORM_FILE = new IFileElementType(FormLanguage.INSTANCE);

  FormElementType SYMBOL = new FormElementType("SYMBOL");
  FormElementType SYMBOLS_DECLARATION = new FormElementType("SYMBOLS_DECLARATION");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == SYMBOL) {
        return new FormSymbolImpl(node);
      }
      else if (type == SYMBOLS_DECLARATION) {
        return new FormSymbolsDeclarationStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
