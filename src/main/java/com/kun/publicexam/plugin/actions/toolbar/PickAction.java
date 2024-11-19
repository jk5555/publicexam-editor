package com.kun.publicexam.plugin.actions.toolbar;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.actions.AbstractAction;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.manager.ViewManager;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;

/**
 * @author shuzijun
 */
public class PickAction extends AbstractAction implements DumbAware {
    private int i = 0;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config) {
        NavigatorAction navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        ViewManager.pick(anActionEvent.getProject(), navigatorAction.getPageInfo());
    }
}
