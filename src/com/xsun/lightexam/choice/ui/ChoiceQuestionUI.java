package com.xsun.lightexam.choice.ui;

import com.xsun.lightexam.choice.ChoiceQuestion;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by xsun on 2016/6/15.
 */
public class ChoiceQuestionUI extends JFrame {

    private JPanel jp1,jp2,jp3,jp4,jp5,jp6;
    private JTextArea jta;
    private JRadioButton jrb1,jrb2,jrb3,jrb4;
    private JLabel op1,op2,op3,op4;
    private JButton jb1,jb2;
    private ButtonGroup bg;

    private GridLayout gl1,gl2;
    private FlowLayout fl1;
    private BorderLayout bl1,bl2,bl3;

    private ChoiceQuestion cq;

    public ChoiceQuestionUI(){
        initUI();
    }

    public ChoiceQuestionUI(ChoiceQuestion cq){
        this();
        this.cq = cq;
        update();
    }

    private void initUI(){
//        gl = new GridLayout(4, 2, 5, 5);
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

        setTitle("选择题");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void update(){
        jta.setText(cq.getStem());
        op1.setText(cq.getOp1());
        op2.setText(cq.getOp2());
        op3.setText(cq.getOp3());
        op4.setText(cq.getOp4());
    }


    public static void main(String[] args) throws IOException{
        ChoiceQuestionReader reader = new ChoiceQuestionReader();
        String json = FileUtils.readFileToString(new File("G:\\LightExam\\res\\choice.json"), "UTF-8");
        ChoiceQuestion question = (ChoiceQuestion) reader.readQuestion(json);
        ChoiceQuestionUI ui = new ChoiceQuestionUI(question);
        EventQueue.invokeLater(() -> ui.setVisible(true));
    }
}
