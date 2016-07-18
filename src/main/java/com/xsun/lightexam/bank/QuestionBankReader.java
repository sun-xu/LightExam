package com.xsun.lightexam.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.QuestionRegistry;

import java.util.Properties;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBankReader {

    private Gson gson;

    public QuestionBankReader(){
        GsonBuilder builder = new GsonBuilder();
        try {
            QuestionRegistry registry = LightExam.getInstance().getQuestionRegistry();
            for (Properties properties : registry.getRegistry()) {
                String questionClassName = properties.getProperty("Question");
                String deserializerClassName = properties.getProperty("Reader");
                if (deserializerClassName != null) {
                    Class<?> questionClass = Class.forName(questionClassName);
                    Object deserializer = Class.forName(deserializerClassName).newInstance();
                    builder.registerTypeAdapter(questionClass, deserializer);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        builder.registerTypeAdapter(QuestionBank.class, new BankDeserializer(builder.create()));
        gson = builder.create();
    }

    public QuestionBank readBank(String json){
        return gson.fromJson(json, QuestionBank.class);
    }

}
