package com.kun.publicexam.plugin.actions.editor;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.kun.publicexam.plugin.manager.CodeManager;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Question;

/**
 * @author shuzijun
 */
public class SubmitAction extends AbstractEditAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config, Question question) {
        CodeManager.SubmitCode(question.getTitleSlug(), anActionEvent.getProject());
    }
}
