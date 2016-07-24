package com.xsun.lightexam.weboperation;

import com.xsun.lightexam.operation.OperationQuestion;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by xsun on 2016/7/24.
 */
public class WebOperationQuestion extends OperationQuestion {

    private String host;
    private String webroot;
    private String target;

    public WebOperationQuestion(String requirement, String host, String webroot, String target) {
        super(requirement);
        this.host = host;
        this.webroot = webroot;
        this.target = target;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("requirement", getRequirement())
                .append("host", host)
                .append("webroot", webroot)
                .append("target", target)
                .toString();
    }
}
