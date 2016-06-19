package com.xsun.lightexam.bank;

import com.google.gson.*;
import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionBank;
import com.xsun.lightexam.api.QuestionHolder;
import com.xsun.lightexam.api.QuestionUi;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
        List<QuestionHolder> qhs = new ArrayList<>();
        for(JsonElement element : array) {
            JsonObject object = (JsonObject) element;
            String name = object.get("name").getAsString();
            String questionType = object.get("question-type").getAsString();
            String conf = object.get("conf").getAsString();
            String uiType = object.get("ui-type").getAsString();
            Question question = null;
            QuestionUi questionUi = null;
            try {
                Class questionClass = Class.forName(questionType);
                String confJson = FileUtils.readFileToString(FileUtils.getFile(new File("G:\\LightExam\\res"), conf), "UTF-8");
                question = (Question) gson.fromJson(confJson, questionClass);
                questionUi = (QuestionUi) Class.forName(uiType).getConstructor(questionClass).newInstance(question);
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | IOException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
            qhs.add(new QuestionHolder(name, question, questionUi));
        }
        return new QuestionBank(qhs);
    }
}
