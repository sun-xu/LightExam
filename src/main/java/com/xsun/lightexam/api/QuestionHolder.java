package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/6/18.
 */
public class QuestionHolder {

    String name;
    Question question;
    QuestionUi questionUi;

    public QuestionHolder(String name, Question question, QuestionUi questionUi) {
        this.name = name;
        this.question = question;
        this.questionUi = questionUi;
    }

    public String getName() {
        return name;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionUi getQuestionUi() {
        return questionUi;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionHolder{");
        sb.append("name='").append(name).append('\'');
        sb.append(", question=").append(question);
        sb.append(", questionUi=").append(questionUi);
        sb.append('}');
        return sb.toString();
    }
}
