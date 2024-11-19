package com.kun.publicexam.plugin.actions.toolbar;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.DumbAware;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.utils.DataKeys;
import com.kun.publicexam.plugin.window.WindowFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author shuzijun
 */
public class FindAction extends ToggleAction implements DumbAware {

    private int i = 0;

    @Override
    public boolean isSelected(AnActionEvent anActionEvent) {
        if (anActionEvent.getProject() == null) {
            //Why is it null?
            return false;
        }
        NavigatorAction navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        if (navigatorAction == null) {
            return false;
        }
        JPanel panel = navigatorAction.queryPanel();
        if (panel == null) {
            return false;
        }
        return panel.isVisible();
    }

    @Override
    public void setSelected(AnActionEvent anActionEvent, boolean b) {
        NavigatorAction navigatorAction = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_NAVIGATORACTION);
        if (navigatorAction == null) {
            return;
        }
        JPanel panel = navigatorAction.queryPanel();
        if (panel == null) {
            return;
        }
        panel.setVisible(b);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return  ActionUpdateThread.BGT;
    }
}
