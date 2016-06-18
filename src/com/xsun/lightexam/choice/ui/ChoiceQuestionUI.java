package com.xsun.lightexam.choice.ui;

import com.xsun.lightexam.api.QuestionUi;
import com.xsun.lightexam.choice.ChoiceQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xsun on 2016/6/15.
 */
public class ChoiceQuestionUI extends QuestionUi<ChoiceQuestion> {

    private JPanel jp1, jp2, jp3, jp4, jp5, jp6;
    private JTextArea jta;
    private JRadioButton jrb1, jrb2, jrb3, jrb4;
    private JLabel op1, op2, op3, op4;
    private JButton jb1, jb2;
    private ButtonGroup bg;

    private GridLayout gl1, gl2;
    private FlowLayout fl1;
    private BorderLayout bl1, bl2, bl3;

    public ChoiceQuestionUI(ChoiceQuestion cq) {
        super(cq);
        initUI();
        update();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        fl1 = new FlowLayout(FlowLayout.LEFT);
        gl1 = new GridLayout(4, 1, 5, 5);
        gl2 = new GridLayout(4, 1, 5, 5);
        bl1 = new BorderLayout();
        bl2 = new BorderLayout();
        bl3 = new BorderLayout();

        jp1 = new JPanel(bl1);
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        jp1.add(jta, BorderLayout.CENTER);

        jp2 = new JPanel(bl2);

        jp3 = new JPanel(bl3);

        jp5 = new JPanel(gl1);
        jrb1 = new JRadioButton("A");
        jrb2 = new JRadioButton("B");
        jrb3 = new JRadioButton("C");
        jrb4 = new JRadioButton("D");
        ActionListener actionListener = e -> {
            switch (e.getActionCommand()) {
                case "A":
                    getQuestion().setChosen(1);
                    break;
                case "B":
                    getQuestion().setChosen(2);
                    break;
                case "C":
                    getQuestion().setChosen(3);
                    break;
                case "D":
                    getQuestion().setChosen(4);
                    break;
            }
        };
        jrb1.addActionListener(actionListener);
        jrb2.addActionListener(actionListener);
        jrb3.addActionListener(actionListener);
        jrb4.addActionListener(actionListener);
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        bg.add(jrb4);
        jp5.add(jrb1);
        jp5.add(jrb2);
        jp5.add(jrb3);
        jp5.add(jrb4);

        jp6 = new JPanel(gl2);
        op1 = new JLabel();
        op2 = new JLabel();
        op3 = new JLabel();
        op4 = new JLabel();
        jp6.add(op1);
        jp6.add(op2);
        jp6.add(op3);
        jp6.add(op4);

        jp3.add(jp5, BorderLayout.WEST);
        jp3.add(jp6, BorderLayout.CENTER);

        jp2.add(jp3, BorderLayout.CENTER);

        jp4 = new JPanel(fl1);
        jb1 = new JButton("上一题");
        jb2 = new JButton("下一题");
        jp4.add(jb1);
        jp4.add(jb2);

        jp2.add(jp4, BorderLayout.SOUTH);

        add(jp1, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
    }

    public void update() {
        ChoiceQuestion cq = getQuestion();
        jta.setText(cq.getStem());
        op1.setText(cq.getOp1());
        op2.setText(cq.getOp2());
        op3.setText(cq.getOp3());
        op4.setText(cq.getOp4());
        switch (cq.getChosen()) {
            case 1:
                jrb1.setSelected(true);
                break;
            case 2:
                jrb2.setSelected(true);
                break;
            case 3:
                jrb3.setSelected(true);
                break;
            case 4:
                jrb4.setSelected(true);
                break;
            default:
                bg.clearSelection();
        }
    }
}
