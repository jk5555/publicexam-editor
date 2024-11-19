package com.kun.publicexam.plugin.editor.converge;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.WeighedFileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.kun.publicexam.plugin.model.LeetcodeEditor;
import com.kun.publicexam.plugin.model.PluginConstant;
import com.kun.publicexam.plugin.setting.ProjectConfig;
import org.jetbrains.annotations.NotNull;

/**
 * @author shuzijun
 */
public class SolutionProvider extends WeighedFileEditorProvider {


    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return true;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        LeetcodeEditor leetcodeEditor = ProjectConfig.getInstance(project).getEditor(file.getPath());
        return new SolutionPreview(project, leetcodeEditor);
    }

    @Override
    public @NotNull String getEditorTypeId() {
        return PluginConstant.LEETCODE_EDITOR_TAB_VIEW + " Solution view";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
