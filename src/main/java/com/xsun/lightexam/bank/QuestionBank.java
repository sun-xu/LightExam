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

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.JsonAdapter;
import com.xsun.lightexam.api.Question;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBank {

    private String title;
    private long time;
    @JsonAdapter(QuestionsAdapter.class)
    private List<Question> questions;

    public QuestionBank(String title, long time, List<Question> questions) {
        this.title = title;
        this.time = time;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("time", time)
                .append("questions", questions)
                .toString();
    }

    public static QuestionBank fromJson(String json) {
        return new GsonBuilder().create().fromJson(json, QuestionBank.class);
    }
}
