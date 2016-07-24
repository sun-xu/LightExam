package com.xsun.lightexam.operation.ui;

import com.xsun.lightexam.operation.OperationQuestion;

import javax.swing.*;
import java.awt.*;

/**
 * Created by xsun on 2016/7/24.
 */
public class OperationQuestionUI extends JFrame {

    private String requirement;

    private JLabel jl;
    private JTextArea jta;
    private JButton jb;
    private JPanel jp1;
    private JPanel jp2;

    public OperationQuestionUI(OperationQuestion operationQuestion){
        requirement = operationQuestion.getRequirement();
        initUI();
    }

    private void initUI(){
        jl = new JLabel("操作要求:");
        jp1 = new JPanel();
        jp1.add(jl);

        jta = new JTextArea(requirement);
        jta.setLineWrap(true);
        jta.setEditable(false);

        jb = new JButton("返回");
        jb.addActionListener(e -> setVisible(false));
        jp2 = new JPanel();
        ((FlowLayout)jp2.getLayout()).setAlignment(FlowLayout.RIGHT);
        jp2.add(jb);

        setLayout(new BorderLayout(10, 10));
        add(jp1, BorderLayout.NORTH);
        add(jta, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(400, 500);
    }

}
