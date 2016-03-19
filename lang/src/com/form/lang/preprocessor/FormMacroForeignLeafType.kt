package com.form.lang.preprocessor

import com.intellij.lang.ForeignLeafType
import com.intellij.psi.tree.IElementType

class FormMacroForeignLeafType(type: IElementType, tokenText: CharSequence, val macroName: String) :
        ForeignLeafType(type, tokenText) {
    //        private val myMacroName: String?
    //    private val myMacroArgumentIndex: Int
    //    private var myOffsetInTopSubstitution: Int = 0
    //    private val myRangeInMacroArgument: TextRange?
    //
    //
    //    fun plungeIntoSubstitution(offsetInSubstitution: Int) {
    //        myOffsetInTopSubstitution = offsetInSubstitution
    //    }
    //
    //    fun getOffsetInTopSubstitution(): Int {
    //        return myOffsetInTopSubstitution
    //    }
    //
    //    fun getRangeInMacroArgument(): TextRange {
    //        return myRangeInMacroArgument
    //    }
    //
    //    fun getMacroArgumentIndex(): Int {
    //        return myMacroArgumentIndex
    //    }
    //
    //    override fun equals(o: Any?): Boolean {
    //        if (this === o) return true
    //        if (o == null || javaClass != o.javaClass) return false
    //
    //        val type = o as OCMacroForeignLeafType?
    //
    //        if (myMacroArgumentIndex != type.myMacroArgumentIndex) return false
    //        if (myOffsetInTopSubstitution != type.myOffsetInTopSubstitution) return false
    //        if (if (myMacroName != null) myMacroName != type.myMacroName else type.myMacroName != null) return false
    //        if (if (myRangeInMacroArgument != null)
    //            myRangeInMacroArgument != type.myRangeInMacroArgument
    //        else
    //            type.myRangeInMacroArgument != null) {
    //            return false
    //        }
    //
    //        return true
    //    }
    //
    //    override fun hashCode(): Int {
    //        var result = if (myMacroName != null) myMacroName.hashCode() else 0
    //        result = 31 * result + myMacroArgumentIndex
    //        result = 31 * result + if (myRangeInMacroArgument != null) myRangeInMacroArgument.hashCode() else 0
    //        return result
    //    }
    //
    //    override fun createLeafNode(leafText: CharSequence?): ASTNode {
    //        return OCMacroForeignLeafElement(this, getValue(), myMacroName, myMacroArgumentIndex, myRangeInMacroArgument, myOffsetInTopSubstitution)
    //    }
    //
    //
    override fun toString(): String {
        val builder = StringBuilder("{").append(delegate)
        builder.append(", ").append(macroName)
        //            builder.append(", ").append(myOffsetInTopSubstitution)
        //            if (myMacroArgumentIndex >= 0) {
        //                builder.append(", ").append(myMacroArgumentIndex)
        //            }
        //            if (myRangeInMacroArgument != null) {
        //                builder.append(" -> ").append(myRangeInMacroArgument)
        //            }
        return builder.append("}").toString()
    }
}