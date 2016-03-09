package com.form.lang.preprocessor;

import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Stack;

public class FormInclusionContext {
    @NotNull
    private final Map<String, Stack<FormMacroSymbol>> mySubstitutions = new THashMap<>();

    protected FormInclusionContext() {
    }

    @NotNull
    public static FormInclusionContext empty() {
        return new FormInclusionContext();
    }

    public void define(FormMacroSymbol def) {
        final String name = def.getName();
        if (name == null) return;
        if (!mySubstitutions.containsKey(def.getName())) {
            mySubstitutions.put(def.getName(), new Stack<>());
        }
        mySubstitutions.get(def.getName()).push(def);
    }

    public void redefine(@NotNull FormMacroSymbol def) {
        String name = def.getName();
        if (isUndefined(name)) return;
        mySubstitutions.get(name).pop();
    }

    public void undef(@Nullable String name) {
        if (isUndefined(name)) return;
        mySubstitutions.get(name).pop();
    }

    @Nullable
    public FormMacroSymbol getDefinition(@Nullable String name) {
        if (isUndefined(name)) return null;
        return mySubstitutions.get(name).peek();
    }

    public boolean isUndefined(@Nullable String name) {
        return !isDefined(name);
    }

    public boolean isDefined(@Nullable String name) {
        return name != null && mySubstitutions.containsKey(name) && !mySubstitutions.get(name).isEmpty();
    }

}
