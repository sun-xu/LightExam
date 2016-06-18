package com.xsun.lightexam.textinput.ui;

import com.xsun.lightexam.api.QuestionUi;
import com.xsun.lightexam.textinput.TextInputQuestion;

import javax.swing.*;
import java.awt.*;

/**
 * Created by xsun on 2016/6/18.
 */
public class TextInputQuestionUI extends QuestionUi<TextInputQuestion> {

    private JPanel jp1, jp2;
    private JTextArea jta1, jta2;
    private JButton jb1, jb2;

    public TextInputQuestionUI(TextInputQuestion question) {
        super(question);
        initUI();
        update();
    }

    @Override
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
//        jta2.setEditable(false);
        jp1.add(jta1);
        jp1.add(jta2);

        jp2 = new JPanel();

        jb1 = new JButton("开始");
        jb2 = new JButton("结束");
        jb2.setEnabled(false);
        jp2.add(jb1);
        jp2.add(jb2);

        add(jp1, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
    }
}
