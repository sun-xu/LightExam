package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.util.AbstractQuestion;

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
        final StringBuilder sb = new StringBuilder("TextInputQuestion{");
        sb.append("source='").append(source).append('\'');
        sb.append(", yours='").append(yours).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
