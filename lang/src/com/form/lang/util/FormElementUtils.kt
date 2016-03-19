package com.form.lang.util

import com.intellij.lang.ForeignLeafType
import com.intellij.psi.tree.IElementType

fun getUnwrappedTokeType(baseElement: IElementType?): IElementType? {
    var result = baseElement
    while (result is ForeignLeafType)
        result = result.delegate
    return result
}