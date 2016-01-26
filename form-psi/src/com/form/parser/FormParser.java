// This is a generated file. Not intended for manual editing.
package com.form.parser;

import com.form.lexer.FormTokens;
import com.form.psi.FormElementTypes;
import com.intellij.lang.PsiBuilder;

import static com.intellij.lang.parser.GeneratedParserUtilBase.*;

import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class FormParser implements PsiParser {

    @Override
    @NotNull
    public ASTNode parse(@NotNull IElementType type, @NotNull PsiBuilder builder) {
        FormParsing parsing = new FormParsing(builder);
        builder = adaptBuilder(type, builder, this, null);
        parsing.parseFile(type, builder);
        return builder.getTreeBuilt();
    }

    private PsiBuilder adaptBuilder(IElementType root, PsiBuilder builder, PsiParser parser, TokenSet[] extendsSets) {
        ErrorState state = new ErrorState();
        ErrorState.initState(state, builder, root, extendsSets);
        return new Builder(builder, state, parser);
    }

}
