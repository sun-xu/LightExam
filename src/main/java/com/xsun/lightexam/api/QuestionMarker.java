package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/7/18.
 */
public interface QuestionMarker<Q extends Question> {

    double mark(Q question);

}
