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

package com.xsun.lightexam.operation;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsun on 2016/7/30.
 */
public class CheckPointsAdapter extends TypeAdapter<List<CheckPoint>> {

    @Override
    public void write(JsonWriter out, List<CheckPoint> value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        for (CheckPoint cp : value) {
            out.beginObject();
            out.name("type");
            out.value(cp.getClass().getName());
            out.name("data");
            out.beginArray();
            for (String s : cp.extraData()) {
                out.value(s);
            }
            out.endArray();
            out.endObject();
        }
        out.endArray();
    }

    @Override
    public List<CheckPoint> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        ArrayList<CheckPoint> checkPoints = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            in.nextName();
            String checkPointClassName = in.nextString();
            in.nextName();
            ArrayList<String> data = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                data.add(in.nextString());
            }
            in.endArray();
            in.endObject();
            String[] strings = new String[data.size()];
            for (int i = 0; i < data.size(); i++)
                strings[i] = data.get(i);
            try {
                CheckPoint cp = (CheckPoint) Class.forName(checkPointClassName).getConstructor(String[].class).newInstance((Object) strings);
                checkPoints.add(cp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        in.endArray();
        return checkPoints;
    }

}
