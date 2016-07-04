package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.choice.ui.ChoiceQuestionUI;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/4.
 */
public class ChoiceQuestionUiFactory implements QuestionUiFactory<ChoiceQuestionList> {
    @Override
    public JFrame getQuestionUi(ChoiceQuestionList question) {
        return new ChoiceQuestionUI(question);
    }
}
