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
