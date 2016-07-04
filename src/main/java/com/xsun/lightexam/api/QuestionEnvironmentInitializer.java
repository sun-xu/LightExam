package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/7/4.
 */
public interface QuestionEnvironmentInitializer<Q extends Question> {
    void initEnvironment(Q question);
}
