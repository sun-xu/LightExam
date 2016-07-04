package com.xsun.lightexam.textinput;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by xsun on 2016/6/27.
 */
public class TextInputQuestionReader implements JsonDeserializer<TextInputQuestion> {
    @Override
    public TextInputQuestion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String source = json.getAsJsonObject().get("source").getAsString();
        int timeLimit = json.getAsJsonObject().get("time-limit").getAsInt();
        return new TextInputQuestion(source, timeLimit);
    }
}
