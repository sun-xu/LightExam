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
import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.bank.QuestionBank;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

/**
 * Created by xsun on 2016/6/18.
 */
public class MainUI extends JFrame {

    private QuestionBank bank;

    public MainUI(QuestionBank bank) {
        this.bank = bank;
        initUI();
    }

    private void initUI() {
        setTitle("Light Exam");
        setJMenuBar(createJMenuBar());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                switch (JOptionPane.showOptionDialog(MainUI.this, "您还没有评卷，你是要现在评卷或是直接关闭程序？", "关闭",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{"评卷", "关闭"}, "评卷")) {
                    case 0:
                        LightExam.getInstance().getExamination().mark();
                        break;
                    case 1:
                        LightExam.getInstance().getExamination().stop();
                        break;
                }
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        java.util.List<Properties> registry = LightExam.getInstance().getQuestionRegistry().getRegistry();
        for (int i = 0; i < registry.size(); i++) {
            String envInitClassName = registry.get(i).getProperty("UiFactory");
            QuestionUiFactory uiFactory = null;
            try {
                uiFactory = (QuestionUiFactory) Class.forName(envInitClassName).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            final JFrame jFrame = uiFactory.getQuestionUi(bank.getQuestions().get(i));
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    MainUI.this.setVisible(true);
                }
            });
            JMenu jMenu = new JMenu(bank.getQuestions().get(i).getName());
            jMenu.addMenuListener(new MenuAdapter() {
                @Override
                public void menuSelected(MenuEvent e) {
                    EventQueue.invokeLater(() -> {
                        jFrame.setVisible(true);
                        MainUI.this.setVisible(false);
                    });
                }
            });
            jMenuBar.add(jMenu);
        }
        JMenu jMenu = new JMenu("评卷");
        jMenu.addMenuListener(new MenuAdapter() {
            @Override
            public void menuSelected(MenuEvent e) {
                EventQueue.invokeLater(LightExam.getInstance().getExamination()::mark);
            }
        });
        jMenuBar.add(jMenu);
        return jMenuBar;
    }

    private class MenuAdapter implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {}
        @Override
        public void menuDeselected(MenuEvent e) {}
        @Override
        public void menuCanceled(MenuEvent e) {}
    }
}
