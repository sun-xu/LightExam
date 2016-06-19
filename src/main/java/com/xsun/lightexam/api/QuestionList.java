package com.xsun.lightexam.api;

import java.util.List;

/**
 * Created by xsun on 2016/6/18.
 */
public interface QuestionList<Q extends Question> extends Question, List<Q> {
}
