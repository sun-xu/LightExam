package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/6/18.
 */
public class QuestionHolder<Q extends Question> {

    String name;
    Question question;
    QuestionUi<Q> questionUi;

    public QuestionHolder(String name, Question question, QuestionUi<Q> questionUi) {
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

    public QuestionUi<Q> getQuestionUi() {
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
