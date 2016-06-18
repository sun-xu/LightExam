package com.xsun.lightexam.api;

import javax.swing.*;

/**
 * Created by xsun on 2016/6/18.
 */
public interface UiFactory {

    QuestionUi<? extends Question> getUi();

}
