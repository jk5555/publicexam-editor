package com.kun.publicexam.plugin.actions.editor;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.kun.publicexam.plugin.editor.ConvergePreview;
import com.kun.publicexam.plugin.manager.NoteManager;
import com.kun.publicexam.plugin.model.Config;
import com.kun.publicexam.plugin.model.Question;

/**
 * @author shuzijun
 */
public class ShowNoteAction extends AbstractEditAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config, Question question) {
        if (config.getConvergeEditor() && openConvergeEditor(anActionEvent, new ConvergePreview.TabSelectFileEditorState("Note"))) {
            return;
        }
        NoteManager.show(question.getTitleSlug(), anActionEvent.getProject(), true);
    }
}
