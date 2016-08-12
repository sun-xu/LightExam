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

package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.util.AbstractQuestion;

/**
 * Created by xsun on 2016/6/14.
 */
public class ChoiceQuestion extends AbstractQuestion implements Question {

    private String stem;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String image;
    private int chosen;
    private int answer;

    public ChoiceQuestion(String stem, String op1, String op2, String op3, String op4, String image, int chosen, int answer) {
        this.stem = stem;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.image = image;
        this.chosen = chosen;
        this.answer = answer;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
    }

    public int getChosen() {
        return chosen;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getStem() {
        return stem;
    }

    public int getAnswer() {
        return answer;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("stem", stem)
                .append("op1", op1)
                .append("op2", op2)
                .append("op3", op3)
                .append("op4", op4)
                .append("image", image)
                .append("chosen", chosen)
                .append("answer", answer)
                .toString();
    }
}
