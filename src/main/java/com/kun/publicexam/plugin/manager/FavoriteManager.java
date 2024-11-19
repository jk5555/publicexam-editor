package com.kun.publicexam.plugin.manager;

import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.project.Project;
import com.kun.publicexam.plugin.model.Graphql;
import com.kun.publicexam.plugin.model.Question;
import com.kun.publicexam.plugin.model.Tag;
import com.kun.publicexam.plugin.utils.MessageUtils;
import com.kun.publicexam.plugin.utils.PropertiesUtils;

/**
 * @author shuzijun
 */
public class FavoriteManager {

    public static void addQuestionToFavorite(Tag tag, String titleSlug, Project project) {
        if (!HttpRequestUtils.isLogin(project)) {
            MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("login.not"));
            return;
        }
        Question question = QuestionManager.getQuestionByTitleSlug(titleSlug, project);
        if (question == null) {
            return;
        }

        try {
            HttpResponse response = Graphql.builder().operationName("addQuestionToFavorite")
                    .variables("favoriteIdHash", tag.getSlug()).variables("questionId", question.getQuestionId()).request();
            if (response.getStatusCode() == 200) {
                String body = response.getBody();
                JSONObject object = JSONObject.parseObject(body).getJSONObject("data").getJSONObject("addQuestionToFavorite");
                if (object.getBoolean("ok")) {
                    tag.getQuestions().add(question.getFrontendQuestionId());
                } else {
                    MessageUtils.getInstance(project).showWarnMsg("info", object.getString("error"));
                }
            } else {
                MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("request.failed"));
            }
        } catch (Exception io) {
            MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("request.failed"));
        }
    }

    public static void removeQuestionFromFavorite(Tag tag, String titleSlug, Project project) {
        if (!HttpRequestUtils.isLogin(project)) {
            MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("login.not"));
            return;
        }
        Question question = QuestionManager.getQuestionByTitleSlug(titleSlug, project);
        if (question == null) {
            return;
        }

        try {
            HttpResponse response = Graphql.builder().operationName("removeQuestionFromFavorite")
                    .variables("favoriteIdHash", tag.getSlug()).variables("questionId", question.getQuestionId()).request();
            if (response.getStatusCode() == 200) {
                String body = response.getBody();
                JSONObject object = JSONObject.parseObject(body).getJSONObject("data").getJSONObject("removeQuestionFromFavorite");
                if (object.getBoolean("ok")) {
                    tag.getQuestions().remove(question.getFrontendQuestionId());
                } else {
                    MessageUtils.getInstance(project).showWarnMsg("info", object.getString("error"));
                }
            } else {
                MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("request.failed"));
            }
        } catch (Exception io) {
            MessageUtils.getInstance(project).showWarnMsg("info", PropertiesUtils.getInfo("request.failed"));
        }
    }
}
