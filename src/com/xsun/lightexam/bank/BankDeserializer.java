package com.xsun.lightexam.bank;

import com.google.gson.*;
import com.xsun.lightexam.api.QuestionBank;
import com.xsun.lightexam.api.QuestionConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xsun on 2016/6/15.
 */
public class BankDeserializer implements JsonDeserializer<QuestionBank> {
    @Override
    public QuestionBank deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonArray jsonSubs = object.get("subs").getAsJsonArray();
        Collection<QuestionConfig> subs = new ArrayList<>();
        for(JsonElement e : jsonSubs){
            subs.add(context.deserialize(e, QuestionConfig.class));
        }
        return new QuestionBank(subs.toArray(new QuestionConfig[]{}));
    }
}
