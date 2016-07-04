package com.xsun.lightexam;

import com.xsun.lightexam.api.QuestionEnvironmentInitializer;
import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.gui.MainUI;

import javax.swing.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by xsun on 2016/6/18.
 */
public class Examination {

    private QuestionBank questionBank;

    public Examination(QuestionBank qb){
        questionBank = qb;
    }

    public void start() {
        initExamEnv();
        JFrame jFrame = initUi();
    }

    private void initExamEnv() {
        List<Properties> registry = LightExam.getInstance().getQuestionRegistry().getRegistry();
        for (int i = 0; i <= registry.size(); i++) {
            String envInitClassName;
            if ((envInitClassName = registry.get(i).getProperty("EnvInit")) != null) {
                QuestionEnvironmentInitializer initializer = null;
                try {
                    initializer = (QuestionEnvironmentInitializer) Class.forName(envInitClassName).newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                initializer.initEnvironment(questionBank.getQuestions().get(i));
            }
        }

    }

    private JFrame initUi() {
        return new MainUI(questionBank);
    }


}
