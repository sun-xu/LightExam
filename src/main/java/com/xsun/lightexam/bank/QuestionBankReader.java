package com.xsun.lightexam.bank;

import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBankReader {

    private Gson gson;

    public QuestionBankReader(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson0 = builder.create();
        try {
            JsonArray registry = gson0.fromJson(FileUtils.readFileToString(
                    new File("G:\\LightExam\\res\\question-registry.json"), "UTF-8"), JsonArray.class);
            for(JsonElement e : registry){
                JsonObject object = (JsonObject) e;
                String questionClassName = object.get("Question").getAsString();
                String deserializerClassName = object.get("Deserializer").getAsString();
                if(!deserializerClassName.equals("$default")) {
                    Class<?> questionClass = Class.forName(questionClassName);
                    Object deserializer = Class.forName(deserializerClassName).newInstance();
                    builder.registerTypeAdapter(questionClass, deserializer);
                }
            }
        } catch (IOException | ClassNotFoundException
                |InstantiationException | IllegalAccessException e) {
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
