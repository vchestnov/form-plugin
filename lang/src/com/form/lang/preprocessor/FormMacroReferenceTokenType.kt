package com.form.lang.preprocessor

import com.intellij.lang.ASTNode
import com.intellij.lang.TokenWrapper
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.ILeafElementType

class FormMacroReferenceTokenType(
        delegate: IElementType,
        value: CharSequence,
        val paramToken: Boolean,
        val macroLevel: Int,
        val isRoot: Boolean
) : TokenWrapper(delegate, value), ILeafElementType {
    override fun createLeafNode(leafText: CharSequence): ASTNode {
        return LeafPsiElement(delegate, value)
    }

    override fun toString(): String {
        val builder = StringBuilder("[").append(delegate).append(", ")
        if (paramToken) {
            builder.append("P-")
        }

        if (isRoot) {
            builder.append("R-")
        }

        builder.append(macroLevel).append("]")
        return builder.toString()
    }
}