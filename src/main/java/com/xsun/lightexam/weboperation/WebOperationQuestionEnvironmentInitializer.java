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

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.api.QuestionEnvironmentInitializer;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import org.apache.commons.io.FileUtils;

import static io.undertow.Handlers.ipAccessControl;
import static io.undertow.Handlers.resource;

/**
 * Created by xsun on 2016/7/24.
 */
public class WebOperationQuestionEnvironmentInitializer implements QuestionEnvironmentInitializer<WebOperationQuestion> {
    @Override
    public void initEnvironment(WebOperationQuestion question) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "127.0.0.1")
                .setHandler(
                        ipAccessControl(
//                                virtualHost(
                                        resource(
                                                new FileResourceManager(
                                                        FileUtils.getFile(
                                                                LightExam.getInstance().getBankPath(), question.getWebroot())
                                                        , 1024
                                                )

                                        )
//                                        , question.getHost()
//                                )
                                , false
                        ).addAllow("127.0.0.1")
                ).build();
        server.start();
    }
}
