package com.kun.publicexam.plugin.actions.tree;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.kun.publicexam.plugin.manager.CodeManager;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Question;

/**
 * @author shuzijun
 */
public class OpenContentAction extends AbstractTreeAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config, Question question) {
        Project project = anActionEvent.getProject();
        CodeManager.openContent(question.getTitleSlug(), project, true);
    }
}
