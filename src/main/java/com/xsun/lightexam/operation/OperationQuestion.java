package com.xsun.lightexam.operation;

import com.xsun.lightexam.util.AbstractQuestion;

/**
 * Created by xsun on 2016/7/24.
 */
public class OperationQuestion extends AbstractQuestion {

    private String requirement;

    public OperationQuestion(String requirement) {
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
