package com.xsun.lightexam.bank;

import com.google.gson.*;
import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.api.Question;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
            JsonObject object = (JsonObject) element;
            String name = object.get("name").getAsString();
            String questionType = object.get("question-type").getAsString();
            String conf = object.get("conf").getAsString();
            Question question = null;
            try {
                Class questionClass = Class.forName(questionType);
                String confJson = FileUtils.readFileToString(
                        FileUtils.getFile(LightExam.getInstance().getBankPath(), conf), "UTF-8");
                question = (Question) gson.fromJson(confJson, questionClass);
                question.setName(name);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            questions.add(question);
        }
        return new QuestionBank(questions);
    }
}
