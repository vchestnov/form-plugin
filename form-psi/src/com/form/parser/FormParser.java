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
public class FormParser implements PsiParser, LightPsiParser {

   @Override @NotNull public ASTNode parse(@NotNull IElementType type, @NotNull PsiBuilder builder) {
    parseLight(type, builder);
    return builder.getTreeBuilt();
  }

  public void parseLight(IElementType type, PsiBuilder builder) {
    boolean r, s;
    builder = adaptBuilder(type, builder, this, null);
    parseFile(type, builder);
  }

  private void parseFile(IElementType root, PsiBuilder builder) {
    final PsiBuilder.Marker rootMarker = builder.mark();
    while (!builder.eof()) {
      parseSymbolsDeclarationStatement(builder);
    }
    rootMarker.done(root);
  }

  private void parseSymbolsDeclarationStatement(PsiBuilder builder){
    if (builder.getTokenType() == FormTokens.SYMBOLS_KEYWORD) {
      final PsiBuilder.Marker symbolsDeclaration = builder.mark();

      do {
        builder.advanceLexer();
        parseSymbol(builder);
      } while (builder.getTokenType() == FormTokens.COMMA);

      if(builder.getTokenType() == FormTokens.SEMICOLON){
        builder.advanceLexer();
      }else{
        builder.advanceLexer();
        builder.error("Semicolon expected");
      }
      symbolsDeclaration.done(FormElementTypes.SYMBOLS_DECLARATION);
    }
    else {
      builder.advanceLexer();
      builder.error("Symbols keyword expected");
    }
  }

  private void parseSymbol(PsiBuilder builder){
    if (builder.getTokenType() == FormTokens.IDENTIFIER) {
      final PsiBuilder.Marker symbolIdentifier = builder.mark();
      builder.advanceLexer();
      symbolIdentifier.done(FormElementTypes.SYMBOL);
    } else {
      builder.advanceLexer();
      builder.error("Symbol name expected");
    }
  }

  private PsiBuilder adaptBuilder (IElementType root, PsiBuilder builder, PsiParser parser, TokenSet[] extendsSets) {
    ErrorState state = new ErrorState();
    ErrorState.initState(state, builder, root, extendsSets);
    return new Builder(builder, state, parser);
  }

}
