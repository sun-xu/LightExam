package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.Question;

/**
 * Created by xsun on 2016/6/18.
 */
public class TextInputQuestion implements Question {

    String source, yours;

    public TextInputQuestion(String source) {
        this.source = source;
        yours = "";
    }

    public String getSource() {
        return source;
    }

    public String getYours() {
        return yours;
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
