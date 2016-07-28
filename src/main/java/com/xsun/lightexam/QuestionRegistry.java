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
