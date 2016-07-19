package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.QuestionMarker;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xsun on 2016/7/18.
 */
public class TextInputQuestionMarker implements QuestionMarker<TextInputQuestion> {

    @Override
    public double mark(TextInputQuestion question) {
        return 1 - (StringUtils.getLevenshteinDistance(question.getYours(), question.getSource()) / 1.0d /
                Math.max(question.getYours().length(), question.getSource().length()));
    }
}
