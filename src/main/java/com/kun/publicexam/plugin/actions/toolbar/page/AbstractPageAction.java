package com.kun.publicexam.plugin.actions.toolbar.page;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;

/**
 * @author shuzijun
 */
public abstract class AbstractPageAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        NavigatorAction navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        if (navigatorAction == null) {
            return;
        }

        actionPerformed(anActionEvent, navigatorAction);
    }

    public abstract void actionPerformed(AnActionEvent anActionEvent, NavigatorAction navigatorAction);
}
