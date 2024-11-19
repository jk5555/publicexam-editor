package com.kun.publicexam.plugin.editor;

import com.intellij.ide.FileIconPatcher;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.LeetcodeEditor;
import com.kun.publicexam.plugin.setting.PersistentConfig;
import com.kun.publicexam.plugin.setting.ProjectConfig;
import com.kun.publicexam.plugin.utils.LogUtils;
import icons.LeetCodeEditorIcons;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

/**
 * @author shuzijun
 */
public class QuestionEditorIconProvider implements FileIconPatcher {

    @Override
    public Icon patchIcon(Icon baseIcon, VirtualFile file, int flags, @Nullable Project project) {
        if (project == null) {
            return baseIcon;
        }
        try {
            Config config = PersistentConfig.getInstance().getInitConfig();
            if (config == null || !config.isShowQuestionEditor() || !config.isShowQuestionEditorSign()) {
                return baseIcon;
            }
            LeetcodeEditor leetcodeEditor = ProjectConfig.getInstance(project).getEditor(file.getPath(), config.getUrl());
            if (leetcodeEditor == null || StringUtils.isBlank(leetcodeEditor.getContentPath())) {
                return baseIcon;
            }
            File contentFile = new File(leetcodeEditor.getContentPath());
            if (!contentFile.exists()) {
                return baseIcon;
            }
        } catch (Throwable e) {
            LogUtils.LOG.error("QuestionEditorIconProvider -> patchIcon", e);
            return baseIcon;
        }
        return LeetCodeEditorIcons.LEETCODE_TOOL_WINDOW;
    }
}
