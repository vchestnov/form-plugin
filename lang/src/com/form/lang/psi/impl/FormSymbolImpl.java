// This is a generated file. Not intended for manual editing.
package com.form.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.form.lang.psi.FormElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.form.lang.psi.*;

public class FormSymbolImpl extends ASTWrapperPsiElement implements FormSymbol {

  public FormSymbolImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FormVisitor) ((FormVisitor)visitor).visitSymbol(this);
    else super.accept(visitor);
  }

}
