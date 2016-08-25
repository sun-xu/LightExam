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

package com.xsun.lightexam.textinput.ui;

import com.xsun.lightexam.textinput.TextInputQuestion;
import com.xsun.lightexam.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xsun on 2016/6/18.
 */
public class TextInputQuestionUI extends JFrame {

    private class StartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jb1.setEnabled(false);
            jb2.setEnabled(true);
            jta2.setEditable(true);
            timer.run();
        }

    }

    private class StopButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stopCountDown();
            jta2.setEditable(false);
            jb2.setEnabled(false);
            jb1.setEnabled(true);
            getQuestion().setYours(jta2.getText());
        }

    }

    private JPanel jp1, jp2;
    private JTextArea jta1, jta2;
    private JButton jb1, jb2;
    private Timer timer;
    private TextInputQuestion question;

    public TextInputQuestionUI(TextInputQuestion question) {
        this.question = question;
        timer = new Timer(question.getTimeLimit());
        initUI();
        update();
        setSize(new Dimension(600, 350));
    }

    public void update() {
        jta1.setText(getQuestion().getSource());
        jta2.setText(getQuestion().getYours());
    }

    private void initUI() {
        setLayout(new BorderLayout());

        jp1 = new JPanel(new GridLayout(2, 1, 10, 5));

        jta1 = new JTextArea();
        jta1.setLineWrap(true);
        jta1.setEnabled(false);
        jta2 = new JTextArea();
        jta2.setLineWrap(true);
        jta2.setEditable(false);
        jp1.add(jta1);
        jp1.add(jta2);

        jp2 = new JPanel();

        jb1 = new JButton("开始");
        jb1.addActionListener(new StartButtonListener());
        jb2 = new JButton("结束");
        jb2.addActionListener(new StopButtonListener());
        jb2.setEnabled(false);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(Timer.TimerUI.create(timer));

        add(jp1, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
    }

    public TextInputQuestion getQuestion() {
        return question;
    }
}
