/*
 * Copyright [2016] [xsun]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
