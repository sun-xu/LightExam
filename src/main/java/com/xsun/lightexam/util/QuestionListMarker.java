package com.xsun.lightexam.util;

import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionMarker;

/**
 * Created by xsun on 2016/7/18.
 */
public abstract class QuestionListMarker<Q extends QuestionList<? extends Question>> implements QuestionMarker<Q> {

    @Override
    public double mark(Q question) {
        return question.stream().mapToDouble(getMarker()::mark).average().getAsDouble();
    }

    public abstract <Q extends Question> QuestionMarker<Q> getMarker();
}
