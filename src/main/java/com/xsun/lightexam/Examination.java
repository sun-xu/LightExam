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

import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionEnvironmentInitializer;
import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.gui.MainUI;
import com.xsun.lightexam.gui.MarkingUI;
import com.xsun.lightexam.login.Examinee;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xsun on 2016/6/18.
 */
public class Examination {

    private QuestionBank questionBank;
    private Examinee examinee;
    private File examDir;

    public Examination(QuestionBank questionBank, Examinee examinee) {
        this.questionBank = questionBank;
        this.examinee = examinee;
    }

    public void start() {
        initExamEnv();
        JFrame jFrame = initUi();
        EventQueue.invokeLater(() -> jFrame.setVisible(true));
    }

    @SuppressWarnings("unchecked")
    private void initExamEnv() {
        examDir = new File("d:\\exam");
        if (examDir.exists()) {
            examDir.delete();
        }
        examDir.mkdirs();
        examDir.deleteOnExit();
        List<Properties> registry = LightExam.getInstance().getQuestionRegistry().getRegistry();
        Map<String, String> initers = new HashMap<>();
        for (Properties p : registry) {
            initers.put(p.getProperty("Question"), p.getProperty("EnvInit"));
        }
        List<Question> questions = questionBank.getQuestions();
        for (Question q : questions) {
            String initerClassName = initers.get(q.getClass().getName());
            if (initerClassName != null) {
                try {

                    QuestionEnvironmentInitializer initer =
                            (QuestionEnvironmentInitializer)
                                    Class.forName(initerClassName).newInstance();
                    initer.initEnvironment(q);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        return new MainUI(questionBank, examinee);
    }

    public File getExamDir() {
        return examDir;
    }

    public Examinee getExaminee() {
        return examinee;
    }
}
