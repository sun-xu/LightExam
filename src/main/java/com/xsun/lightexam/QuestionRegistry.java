package com.xsun.lightexam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.util.*;
import java.util.stream.Collectors;

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
        ArrayList registry0 = g.fromJson(json, ArrayList.class);
        @SuppressWarnings("unchecked")
        ArrayList<Properties> registry = (ArrayList<Properties>) registry0.stream().map(e -> {
            LinkedTreeMap ltm = (LinkedTreeMap) e;
            Set<Map.Entry> entryset = ltm.entrySet();
            Properties p = new Properties();
            entryset.forEach(entry -> p.put(entry.getKey(), entry.getValue()));
            return p;
        }).collect(Collectors.toList());
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
