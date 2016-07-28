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

package com.xsun.lightexam;

import com.xsun.lightexam.api.QuestionEnvironmentInitializer;
import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.gui.MainUI;
import com.xsun.lightexam.gui.MarkingUI;

import javax.swing.*;
import java.awt.*;
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
        EventQueue.invokeLater(() -> jFrame.setVisible(true));
    }

    private void initExamEnv() {
        List<Properties> registry = LightExam.getInstance().getQuestionRegistry().getRegistry();
        for (int i = 0; i < registry.size(); i++) {
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

    public void stop() {
        System.exit(0);
    }

    public void mark() {
        EventQueue.invokeLater(() -> new MarkingUI(questionBank).setVisible(true));
    }

    private JFrame initUi() {
        return new MainUI(questionBank);
    }


}
