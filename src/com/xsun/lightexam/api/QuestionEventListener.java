package com.xsun.lightexam.api;

import java.util.EventListener;

/**
 * Created by xsun on 2016/6/14.
 */
public interface QuestionEventListener extends EventListener {

    void preAnswer();
    void onAnswer();
    void postAnswer();

}
