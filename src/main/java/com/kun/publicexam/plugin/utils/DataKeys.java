package com.kun.publicexam.plugin.utils;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.DataKey;
import com.kun.publicexam.plugin.manager.NavigatorAction;
import com.kun.publicexam.plugin.window.NavigatorTabsPanel;

/**
 * @author shuzijun
 */
public class DataKeys {

    public static final DataKey<ConsoleView> LEETCODE_CONSOLE_VIEW = DataKey.create("LEETCODE_CONSOLE_VIEW");

    public static final DataKey<NavigatorTabsPanel> LEETCODE_PROJECTS_TABS = DataKey.create("LEETCODE_PROJECTS_TABS");

    public static final DataKey<NavigatorAction> LEETCODE_PROJECTS_NAVIGATORACTION = DataKey.create("LEETCODE_PROJECTS_NAVIGATORACTION");
}
