package com.xsun.lightexam.operation;

import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.operation.ui.OperationQuestionUI;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/24.
 */
public class OperationQuestionUiFactory<Q extends OperationQuestion> implements QuestionUiFactory<Q> {

    @Override
    public JFrame getQuestionUi(Q question) {
        return new OperationQuestionUI(question);
    }

}
