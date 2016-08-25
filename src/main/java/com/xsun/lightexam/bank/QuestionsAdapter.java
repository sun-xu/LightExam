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
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.QuestionRegistry;
import com.xsun.lightexam.api.Question;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by xsun on 2016/8/25.
 */
public class QuestionsAdapter extends TypeAdapter<List<Question>> {

    private Gson gson;

    public QuestionsAdapter() {
        GsonBuilder builder = new GsonBuilder();
        QuestionRegistry registry = LightExam.getInstance().getQuestionRegistry();
        for (Properties properties : registry.getRegistry()) {
            String questionClassName = properties.getProperty("Question");
            String deserializerClassName = properties.getProperty("Reader");
            if (deserializerClassName != null) {
                try {
                    Class<?> questionClass = Class.forName(questionClassName);
                    Object deserializer = Class.forName(deserializerClassName).newInstance();
                    builder.registerTypeAdapter(questionClass, deserializer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        gson = builder.create();
    }

    @Override
    public void write(JsonWriter out, List<Question> value) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Question> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        ArrayList<Question> questions = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            in.nextName();
            String questionName = in.nextString();
            in.nextName();
            String questionClassName = in.nextString();
            in.nextName();
            String configPath = in.nextString();
            in.endObject();
            String config = FileUtils.readFileToString(
                    FileUtils.getFile(LightExam.getInstance().getBankPath(), configPath), "UTF-8");
            try {
                @SuppressWarnings("unchecked")
                Question q = gson.fromJson(config, (Class<? extends Question>) Class.forName(questionClassName));
                q.setName(questionName);
                questions.add(q);
            } catch (ClassNotFoundException e) {
                System.err.println("在读取" + questionName + "时失败");
                e.printStackTrace();
            }
        }
        in.endArray();
        return questions;
    }
}
