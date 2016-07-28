package com.xsun.lightexam.weboperation;

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.api.QuestionEnvironmentInitializer;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import org.apache.commons.io.FileUtils;

import static io.undertow.Handlers.*;

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
                                virtualHost(
                                        resource(
                                                new FileResourceManager(
                                                        FileUtils.getFile(
                                                                LightExam.getInstance().getBankPath(), question.getWebroot())
                                                        , 1024
                                                )

                                        ), question.getHost()
                                ), false
                        ).addAllow("127.0.0.1")
                ).build();
        server.start();
    }

    public static void main(String[] args) {
        WebOperationQuestion question = new WebOperationQuestion("requirement", "abc.com", "webroot", "");
        WebOperationQuestionEnvironmentInitializer initializer = new WebOperationQuestionEnvironmentInitializer();
        initializer.initEnvironment(question);
    }

}
