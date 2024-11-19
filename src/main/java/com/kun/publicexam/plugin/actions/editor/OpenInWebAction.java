package com.kun.publicexam.plugin.actions.editor;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Question;
import com.kun.publicexam.plugin.utils.URLUtils;

/**
 * @author zzdcon
 */
public class OpenInWebAction extends AbstractEditAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config, Question question) {
        BrowserUtil.browse(URLUtils.getLeetcodeProblems() + question.getTitleSlug());
    }
}
