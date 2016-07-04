package com.xsun.lightexam.choice;

import com.xsun.lightexam.api.Question;

/**
 * Created by xsun on 2016/6/14.
 */
public class ChoiceQuestion implements Question{

    private String name;
    private String stem;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private int chosen = 0;

    public ChoiceQuestion(String stem, String op1, String op2, String op3, String op4) {
        this.stem = stem;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChoiceQuestion{");
        sb.append("chosen=").append(chosen);
        sb.append(", stem='").append(stem).append('\'');
        sb.append(", op1='").append(op1).append('\'');
        sb.append(", op2='").append(op2).append('\'');
        sb.append(", op3='").append(op3).append('\'');
        sb.append(", op4='").append(op4).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
