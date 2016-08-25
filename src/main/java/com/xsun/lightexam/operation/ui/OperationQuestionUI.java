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

package com.xsun.lightexam.operation.ui;

import com.xsun.lightexam.operation.OperationQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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
        jb.addActionListener(e -> {
            setVisible(false);
            processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        jp2 = new JPanel();
        ((FlowLayout)jp2.getLayout()).setAlignment(FlowLayout.RIGHT);
        jp2.add(jb);

        setLayout(new BorderLayout(10, 10));
        add(jp1, BorderLayout.NORTH);
        add(jta, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);

        setSize(500, 400);
    }

}
