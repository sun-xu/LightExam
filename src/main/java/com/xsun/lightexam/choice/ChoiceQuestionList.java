package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.QuestionList;

import java.util.ArrayList;

/**
 * Created by xsun on 2016/6/19.
 */
public class ChoiceQuestionList extends ArrayList<ChoiceQuestion> implements QuestionList<ChoiceQuestion> {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
