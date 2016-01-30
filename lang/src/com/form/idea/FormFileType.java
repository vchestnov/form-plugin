package com.form.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingRegistry;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.charset.Charset;

/**
 * Created by Sem on 19.01.2016.
 */
public class FormFileType extends LanguageFileType {
    public static final LanguageFileType INSTANCE = new FormFileType();
    @NonNls
    public static final String DEFAULT_EXTENSION = "frm";
    @NonNls
    public static final String DOT_DEFAULT_EXTENSION = "." + DEFAULT_EXTENSION;

    private FormFileType() {
        super(FormLanguage.INSTANCE);
    }

    @Override
    @NotNull
    public String getName() {
        return "Form";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Form(https://github.com/vermaseren/form) language file";
    }

    @Override
    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icons/form.png");
    }

    @Override
    public String getCharset(@NotNull VirtualFile file, @NotNull final byte[] content) {
        Charset charset = EncodingRegistry.getInstance().getDefaultCharsetForPropertiesFiles(file);
        if (charset == null) {
            charset = CharsetToolkit.getDefaultSystemCharset();
        }
        return charset.name();
    }
}
