package com.xsun.lightexam.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xsun.lightexam.api.QuestionBank;
import com.xsun.lightexam.api.QuestionConfig;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBankReader {

    private Gson gson;

    public QuestionBankReader(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(QuestionConfig.class, new ConfigDeserializer());
        builder.registerTypeAdapter(QuestionBank.class, new BankDeserializer());
        gson = builder.create();
    }

    public QuestionBank readBank(String json){
        return gson.fromJson(json, QuestionBank.class);
    }

}
