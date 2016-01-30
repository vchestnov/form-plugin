// This is a generated file. Not intended for manual editing.
package com.form.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.form.lang.psi.*;

public class FormSymbolsDeclarationStatementImpl extends FormElementImpl implements FormSymbolsDeclarationStatement {

  public FormSymbolsDeclarationStatementImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<FormSymbol> getSymbolList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FormSymbol.class);
  }

}
