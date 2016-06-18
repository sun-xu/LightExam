package com.xsun.lightexam.api;

import java.util.Arrays;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private Question[] questions;

    public QuestionBank(Question[] questions) {
        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionBank{");
        sb.append("questions=").append(Arrays.toString(questions));
        sb.append('}');
        return sb.toString();
    }
}
