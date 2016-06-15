package com.xsun.lightexam.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xsun.lightexam.api.QuestionBank;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBankReader {

    private Gson gson = new GsonBuilder().create();

    public QuestionBank readBank(String json){
        return gson.fromJson(json, QuestionBank.class);
    }

}
