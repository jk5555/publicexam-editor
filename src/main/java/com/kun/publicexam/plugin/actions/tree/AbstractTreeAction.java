package com.kun.publicexam.plugin.actions.tree;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.actions.AbstractAction;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.manager.QuestionManager;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Question;
import com.kun.publicexam.plugin.model.QuestionView;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;

/**
 * @author shuzijun
 */
public abstract class AbstractTreeAction extends AbstractAction implements DumbAware {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config) {
        NavigatorAction<QuestionView> navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        if (navigatorAction == null) {
            return;
        }
        QuestionView questionView = navigatorAction.getSelectedRowData();
        if (questionView == null) {
            return;
        }
        Question question = QuestionManager.getQuestionByTitleSlug(questionView.getTitleSlug(), anActionEvent.getProject());
        if (question == null) {
            return;
        }
        actionPerformed(anActionEvent, config, question);
    }

    public abstract void actionPerformed(AnActionEvent anActionEvent, Config config, Question question);
}
