package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.textinput.ui.TextInputQuestionUI;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/4.
 */
public class TextInputQuestionUiFactory implements QuestionUiFactory<TextInputQuestion> {
    @Override
    public JFrame getQuestionUi(TextInputQuestion question) {
        return new TextInputQuestionUI(question);
    }
}
