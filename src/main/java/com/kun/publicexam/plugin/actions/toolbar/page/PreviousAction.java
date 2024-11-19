package com.kun.publicexam.plugin.actions.toolbar.page;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.kun.publicexam.plugin.manager.NavigatorAction;

/**
 * @author shuzijun
 */
public class PreviousAction extends AbstractPageAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent, NavigatorAction navigatorAction) {
        navigatorAction.getPagePanel().clickPrevious();
    }
}
