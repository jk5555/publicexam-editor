package com.kun.publicexam.plugin.listener;

import com.intellij.util.messages.Topic;
import com.kun.publicexam.plugin.model.PluginConstant;
import com.kun.publicexam.plugin.model.Question;

/**
 * @author shuzijun
 */
public interface QuestionStatusNotifier {

    @Topic.AppLevel
    Topic<QuestionStatusNotifier> QUESTION_STATUS_TOPIC = Topic.create(PluginConstant.LEETCODE_EDITOR_QUESTION_STATUS_TOPIC, QuestionStatusNotifier.class);

    public void updateTable(Question question);


}
