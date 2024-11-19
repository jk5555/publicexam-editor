package com.kun.publicexam.plugin.actions.tree;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.manager.FavoriteManager;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.manager.QuestionManager;
import com.kun.publicexam.plugin.model.PluginConstant;
import com.kun.publicexam.plugin.model.Question;
import com.kun.publicexam.plugin.model.QuestionView;
import com.kun.publicexam.plugin.model.Tag;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author shuzijun
 */
public class FavoriteAction extends ToggleAction implements DumbAware {

    private Tag tag;

    public FavoriteAction(@Nullable String text, Tag tag) {
        super(text);
        this.tag = tag;
    }

    @Override
    public boolean isSelected(AnActionEvent anActionEvent) {

        NavigatorAction<QuestionView> navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        final QuestionView questionView = navigatorAction.getSelectedRowData();
        if (questionView == null) {
            return false;
        }
        Question cacheQuestion = QuestionManager.getQuestionByTitleSlug(questionView.getTitleSlug(), anActionEvent.getProject(),true);
        if (cacheQuestion == null) {
            return false;
        }
        return tag.getQuestions().contains(cacheQuestion.getQuestionId());
    }

    @Override
    public void setSelected(AnActionEvent anActionEvent, boolean b) {
        NavigatorAction<QuestionView> navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        QuestionView questionView = navigatorAction.getSelectedRowData();
        if (questionView == null) {
            return;
        }

        ProgressManager.getInstance().run(new Task.Backgroundable(anActionEvent.getProject(), PluginConstant.PLUGIN_NAME + ".favorite", false) {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                if (b) {
                    FavoriteManager.addQuestionToFavorite(tag, questionView.getTitleSlug(), anActionEvent.getProject());
                } else {
                    FavoriteManager.removeQuestionFromFavorite(tag, questionView.getTitleSlug(), anActionEvent.getProject());
                }
            }
        });

    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return  ActionUpdateThread.BGT;
    }
}
