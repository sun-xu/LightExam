package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.QuestionMarker;

/**
 * Created by xsun on 2016/7/18.
 */
public class ChoiceQuestionMarker implements QuestionMarker<ChoiceQuestion> {
    @Override
    public double mark(ChoiceQuestion question) {
        return question.getChosen() == question.getAnwser() ? 1 : 0;
    }
}
