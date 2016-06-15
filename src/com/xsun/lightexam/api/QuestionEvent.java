package com.xsun.lightexam.api;

import java.util.EventObject;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public QuestionEvent(Object source) {
        super(source);
    }


}
