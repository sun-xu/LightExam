package com.xsun.lightexam.api;

import java.util.List;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private List<QuestionHolder> questions;

    public QuestionBank(List<QuestionHolder> questions) {
        this.questions = questions;
    }

    public List<QuestionHolder> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionBank{");
        sb.append("questions=").append(questions);
        sb.append('}');
        return sb.toString();
    }
}
