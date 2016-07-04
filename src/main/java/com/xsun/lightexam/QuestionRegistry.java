package com.xsun.lightexam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by xsun on 2016/7/4.
 */
public class QuestionRegistry {

    private List<Properties> registry;

    private QuestionRegistry(List<Properties> registry) {
        this.registry = registry;
    }

    public static QuestionRegistry getQuestionRegistryFromJson(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson g = builder.create();
        ArrayList<Properties> registry = g.fromJson(json, new ArrayList<Properties>().getClass());
        return new QuestionRegistry(registry);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionRegistry{");
        sb.append("registry=").append(registry);
        sb.append('}');
        return sb.toString();
    }

    public List<Properties> getRegistry() {
        return registry;
    }

    //    public static void main(String[] args) throws IOException {
//        QuestionRegistry registry = getQuestionRegistryFromJson(FileUtils.readFileToString(FileUtils.getFile(System.getProperty("user.dir"), "build", "resources", "main", "config", "question-registry.json"), "UTF-8"));
//        System.out.println(registry);
//    }
}
