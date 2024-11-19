package com.kun.publicexam.plugin.editor.converge;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.kun.publicexam.plugin.editor.LCVProvider;
import com.kun.publicexam.plugin.model.LeetcodeEditor;
import com.kun.publicexam.plugin.setting.ProjectConfig;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author shuzijun
 */
public class ContentProvider extends LCVProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return true;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        LeetcodeEditor leetcodeEditor = ProjectConfig.getInstance(project).getEditor(file.getPath());

        VirtualFile contentVf;
        try {
            contentVf = ApplicationManager.getApplication().executeOnPooledThread(() -> LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(leetcodeEditor.getContentPath()))).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return super.createEditor(project, contentVf);
    }
}
