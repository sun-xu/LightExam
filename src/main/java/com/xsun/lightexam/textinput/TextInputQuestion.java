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

package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.util.AbstractQuestion;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by xsun on 2016/6/18.
 */
public class TextInputQuestion extends AbstractQuestion implements Question {

    private String source, yours;
    private int timeLimit;

    public TextInputQuestion(String source, int timeLimit) {
        this.source = source;
        this.timeLimit = timeLimit;
        yours = "";
    }

    public String getSource() {
        return source;
    }

    public String getYours() {
        return yours;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setYours(String yours) {
        this.yours = yours;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("source", source)
                .append("yours", yours)
                .append("timeLimit", timeLimit)
                .toString();
    }
}
