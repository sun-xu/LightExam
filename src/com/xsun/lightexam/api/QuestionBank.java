package com.xsun.lightexam.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private QuestionConfig[] subs;

    public QuestionBank(QuestionConfig[] subs){
        this.subs = subs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionBank{");
        sb.append("subs=").append(Arrays.toString(subs));
        sb.append('}');
        return sb.toString();
    }
}
