package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.QuestionMarker;
import com.xsun.lightexam.util.QuestionListMarker;

/**
 * Created by xsun on 2016/7/18.
 */
public class ChoiceQuestionListMarker extends QuestionListMarker<ChoiceQuestionList> {

    ChoiceQuestionMarker marker = new ChoiceQuestionMarker();

    @Override
    @SuppressWarnings("unchecked")
    public QuestionMarker<ChoiceQuestion> getMarker() {
        return marker;
    }
}
