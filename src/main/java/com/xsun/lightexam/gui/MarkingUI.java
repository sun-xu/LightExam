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

package com.xsun.lightexam.gui;

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionMarker;
import com.xsun.lightexam.bank.QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xsun on 2016/7/18.
 */
public class MarkingUI extends JFrame {

    private QuestionBank bank;
    private JTextArea jta;

    public MarkingUI(QuestionBank bank) {
        this.bank = bank;
        setTitle("评卷");
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        add(jta, BorderLayout.CENTER);
        JPanel jp = new JPanel();
        JButton jb1 = new JButton("返回");
        jb1.addActionListener(e -> setVisible(false));
        jp.add(jb1);
        JButton jb2 = new JButton("退出");
        jb2.addActionListener(e -> LightExam.getInstance().getExamination().stop());
        jp.add(jb2);
        add(jp, BorderLayout.SOUTH);
        setSize(200, 450);
        new BankMarker().execute();
    }

    private class BankMarker extends SwingWorker<Double, Grade> {

        @Override
        protected Double doInBackground() throws Exception {
            return bank.getQuestions().stream().mapToDouble(
                    question -> {
                        Grade g = new Grade();
                        LightExam.getInstance().getQuestionRegistry().getRegistry().stream()
                                .filter(p -> p.getProperty("Question").equals(question.getClass().getName()))
                                .map(p -> p.getProperty("Marker"))
                                .findFirst().ifPresent(s -> {
                            try {
                                QuestionMarker marker = (QuestionMarker) Class.forName(s).newInstance();
                                g.grade = marker.mark(question);
                                g.question = question;
                                publish(g);
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                        return g.grade;
                    }
            ).average().getAsDouble();
        }

        @Override
        protected void done() {
            try {
                jta.append(String.format("总分: %.1f", get() * 100));
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
                jta.append("评分失败:" + e);
            }
        }

        @Override
        protected void process(List<Grade> chunks) {
            chunks.forEach(
                    grade -> jta.append(String.format("%s得分: %.1f\n", grade.question.getName(), grade.grade * 100))
            );
        }
    }

    private class Grade {
        Question question;
        double grade;
    }
}
