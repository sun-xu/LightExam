package com.xsun.lightexam.weboperation;

import org.testng.annotations.Test;

/**
 * Created by xsun on 2016/7/27.
 */
public class WebOperationQuestionEnvironmentInitializerTest {

    @Test
    public void testInitEnvironment() throws Exception {
        WebOperationQuestion question = new WebOperationQuestion("requirement", "abc.com", "webroot", "");
        WebOperationQuestionEnvironmentInitializer initializer = new WebOperationQuestionEnvironmentInitializer();
        initializer.initEnvironment(question);

    }

}