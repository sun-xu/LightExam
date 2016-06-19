package com.xsun.lightexam.api;

import javax.swing.*;

/**
 * Created by xsun on 2016/6/18.
 */
public abstract class QuestionUi<Q extends Question> extends JPanel{

    private Q question;

    public QuestionUi(Q question) {
        this.question = question;
    }

    public Q getQuestion() {
        return question;
    }

    public void setQuestion(Q question) {
        this.question = question;
        update();
    }

    public abstract void update();
}
