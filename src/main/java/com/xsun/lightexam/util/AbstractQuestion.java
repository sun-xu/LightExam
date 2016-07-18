package com.xsun.lightexam.util;

import com.xsun.lightexam.api.Question;

/**
 * Created by xsun on 2016/7/18.
 */
public class AbstractQuestion implements Question {

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
