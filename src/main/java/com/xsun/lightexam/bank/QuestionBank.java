package com.xsun.lightexam.bank;

import com.xsun.lightexam.api.Question;

import java.util.List;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private List<Question> questions;

    public QuestionBank(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
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
