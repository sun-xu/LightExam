package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/7/4.
 */
public interface QuestionReader<Q extends Question> {
    Q readQuestion(String data);
}
