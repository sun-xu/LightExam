package com.xsun.lightexam.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private Map<String, String> subs;

    public QuestionBank(Map<String, String> subs){
        this.subs = subs;
    }

    String getSubQuestionConfig(String subid){
        return subs.get(subid);
    }

    String[] getSubQuestions(){
        return subs.keySet().toArray(new String[]{});
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionBank{");
        sb.append("subs=").append(subs);
        sb.append('}');
        return sb.toString();
    }
}
