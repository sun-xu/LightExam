package com.xsun.lightexam.choice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionReader;

/**
 * Created by xsun on 2016/6/14.
 */
public class ChoiceQuestionReader implements QuestionReader {

    private Gson gson;

    public ChoiceQuestionReader(){
        gson = new GsonBuilder().create();
    }

    @Override
    public Question readQuestion(String json) {
        return gson.fromJson(json, ChoiceQuestion.class);
    }
}
