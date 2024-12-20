package com.kun.publicexam.plugin.actions.toolbar;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.actions.AbstractAction;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Constant;
import com.kun.publicexam.plugin.model.PluginConstant;
import com.kun.publicexam.plugin.model.Sort;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;
import icons.LeetCodeEditorIcons;
import org.jetbrains.annotations.NotNull;

/**
 * @author shuzijun
 */
public class SortAction extends AbstractAction implements DumbAware {

    @Override
    public boolean displayTextInToolbar() {
        return true;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        NavigatorAction navigatorAction = WindowFactory.getDataContext(e.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        Sort sort = getSort(e, navigatorAction);
        if (sort == null) {
            return;
        }
        if (sort.getType() == Constant.SORT_ASC) {
            e.getPresentation().setIcon(LeetCodeEditorIcons.SORT_ASC);
        } else if (sort.getType() == Constant.SORT_DESC) {
            e.getPresentation().setIcon(LeetCodeEditorIcons.SORT_DESC);
        } else {
            e.getPresentation().setIcon(null);
        }
        navigatorAction.updateUI();
        super.update(e);

    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config) {
        NavigatorAction navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        if (navigatorAction == null) {
            return;
        }
        Sort sort = getSort(anActionEvent, navigatorAction);
        if (sort == null) {
            return;
        }
        navigatorAction.getFind().operationType(getKey(anActionEvent));
        navigatorAction.sort(sort);
    }

    private Sort getSort(AnActionEvent anActionEvent, NavigatorAction navigatorAction) {
        return navigatorAction.getFind().getSort(getKey(anActionEvent));
    }

    private String getKey(AnActionEvent anActionEvent) {
        return anActionEvent.getActionManager().getId(this).replace(PluginConstant.LEETCODE_SORT_PREFIX, "")
                .replace(PluginConstant.LEETCODE_CODETOP_SORT_PREFIX, "")
                .replace(PluginConstant.LEETCODE_ALL_SORT_PREFIX, "");
    }
}
