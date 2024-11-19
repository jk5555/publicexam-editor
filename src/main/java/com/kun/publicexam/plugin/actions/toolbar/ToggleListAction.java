package com.kun.publicexam.plugin.actions.toolbar;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.actions.AbstractAction;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.NavigatorTabsPanel;
import com.kun.publicexam.plugin.window.WindowFactory;

/**
 * @author shuzijun
 */
public class ToggleListAction extends AbstractAction implements DumbAware {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config) {
        NavigatorTabsPanel navigatorTabs = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_TABS);
        if (navigatorTabs == null) {
            return;
        }
        ApplicationManager.getApplication().invokeLater(() -> navigatorTabs.toggle());

    }
}
