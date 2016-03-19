package com.form.lang.parser;

import com.form.lang.lexer.FormTokens;
import com.form.idea.FormLanguage;
import com.form.lang.FormNodeType;
import com.form.lang.preprocessor.FormInclusionContext;
import com.form.lang.preprocessor.FormPreprocessingLexer;
import com.form.lang.psi.FormFile;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class FormParserDefinition implements ParserDefinition {
    @NotNull @Override public TokenSet getWhitespaceTokens() {
        return FormTokens.WHITE_SPACES;
    }

    @NotNull @Override public TokenSet getCommentTokens() {
        return FormTokens.COMMENTS;
    }

    public static final IFileElementType FILE = new IFileElementType(FormLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        FormInclusionContext inclusionContext = FormInclusionContext.empty();
        return new FormPreprocessingLexer(inclusionContext);
    }

    @Override
    public PsiParser createParser(Project project) {
        return new FormParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        FormNodeType elementType = (FormNodeType) node.getElementType();
        return elementType.createPsi(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new FormFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
