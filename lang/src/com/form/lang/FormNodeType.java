package com.form.lang;

import com.form.idea.FormLanguage;
import com.form.lang.psi.FormElement;
import com.form.lang.psi.impl.FormElementImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public class FormNodeType extends IElementType {
    private Constructor<? extends FormElement> myPsiFactory;

    public FormNodeType(@NotNull @NonNls String debugName, Class<? extends FormElement> psiClass) {
        super(debugName, FormLanguage.INSTANCE);
        try {
            myPsiFactory = psiClass != null ? psiClass.getConstructor(ASTNode.class) : null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Must have a constructor with ASTNode");
        }
    }

    public FormElement createPsi(ASTNode node) {
        assert node.getElementType() == this;

        try {
            if (myPsiFactory == null) {
                return new FormElementImpl(node);
            }
            return myPsiFactory.newInstance(node);
        } catch (Exception e) {
            throw new RuntimeException("Error creating psi element for node", e);
        }
    }
}