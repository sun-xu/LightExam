package com.xsun.lightexam.api;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/4.
 */
public interface QuestionUiFactory<Q extends Question> {
    JFrame getQuestionUi(Q question);
}
