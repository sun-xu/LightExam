package com.xsun.lightexam.bank;

import com.google.gson.*;
import com.xsun.lightexam.api.QuestionConfig;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by xsun on 2016/6/15.
 */
public class ConfigDeserializer implements JsonDeserializer<QuestionConfig> {
    @Override
    public QuestionConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Map.Entry<String, JsonElement> e = object.entrySet().iterator().next();
        String id = e.getKey();
        String place = e.getValue().getAsString();
        return new QuestionConfig(id, place);
    }
}
