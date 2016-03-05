package com.form.lang.preprocessor;

import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FormInclusionContext {
    @NotNull
    private final Map<String, FormMacroSymbol> mySubstitutions = new THashMap<String, FormMacroSymbol>();

    protected FormInclusionContext() {
    }

    @NotNull
    public static FormInclusionContext empty() {
        return new FormInclusionContext();
    }

    public void define(FormMacroSymbol def) {
        final String name = def.getName();
        if(canBeDefined(name)) {
            mySubstitutions.put(def.getName(), def);
        }
    }

    private boolean canBeDefined(String name) {
        return name != null;
    }
}
