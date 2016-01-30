package com.form.lang.psi;

import com.form.idea.FormFileType;
import com.form.idea.FormLanguage;
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
