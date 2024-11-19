package com.kun.publicexam.plugin.listener;

import com.intellij.util.messages.Topic;
import com.kun.publicexam.plugin.model.PluginConstant;

/**
 * @author shuzijun
 */
public interface AllQuestionNotifier {

    @Topic.AppLevel
    Topic<AllQuestionNotifier> TOPIC = Topic.create(PluginConstant.LEETCODE_ALL_QUESTION_TOPIC, AllQuestionNotifier.class);

    void reset();
}
