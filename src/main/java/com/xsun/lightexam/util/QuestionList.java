package com.xsun.lightexam.util;

import com.xsun.lightexam.api.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsun on 2016/6/18.
 */
public class QuestionList<Q extends Question> extends ArrayList<Q> implements Question, List<Q> {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
