package com.xsun.lightexam.api;

/**
 * Created by xsun on 2016/6/15.
 */
public class QuestionConfig {

    private String id, place;

    public QuestionConfig() {
    }

    public QuestionConfig(String id, String place) {
        this.id = id;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuestionConfig{");
        sb.append("id='").append(id).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
