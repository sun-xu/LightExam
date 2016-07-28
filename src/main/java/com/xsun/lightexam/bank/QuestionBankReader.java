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
