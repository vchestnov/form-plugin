package com.form.psi;

import com.form.plugin.FormFileType;
import com.form.plugin.FormLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class FormFile extends PsiFileBase{
    public FormFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, FormLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return FormFileType.INSTANCE;
    }
}
