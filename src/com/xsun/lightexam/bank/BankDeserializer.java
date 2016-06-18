package com.xsun.lightexam.bank;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionBank;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xsun on 2016/6/15.
 */
public class BankDeserializer implements JsonDeserializer<QuestionBank> {

    private Gson gson;

    public BankDeserializer(Gson gson){
        this.gson = gson;
    }

    @Override
    public QuestionBank deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = (JsonArray) json;
        List<Question> questions = new ArrayList<>();
        for(JsonElement element : array) {
            try {
                JsonObject object = (JsonObject) element;
                Class<?> questionType = Class.forName(object.get("type").getAsString());
                String questionJson = FileUtils.readFileToString(FileUtils.getFile(
                        new File("G:\\LightExam\\res"), object.get("conf").getAsString()), "UTF-8");
                Object question = gson.fromJson(questionJson, questionType);
                questions.add((Question) question);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return new QuestionBank(questions.toArray(new Question[]{}));
    }
}
