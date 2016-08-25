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
import com.xsun.lightexam.login.Examinee;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xsun on 2016/6/18.
 */
public class MainUI extends JFrame {

    public static class ExamineeInfoPanel extends JPanel {
        private Examinee examinee;
        private long time;

        public ExamineeInfoPanel(Examinee examinee) {
            this.examinee = examinee;
            LightExam.getInstance().getExamination().addTimerListener(s -> {
                repaint();
                time = s;
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(examinee.getImage() == null ? getDefaultExamineeImage() : examinee.getImage(),
                    10, 10, 150, 200, this);
            g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            g.drawString("考生姓名：" + examinee.getRealName(), 170, 50);
            g.drawString("准考证号：" + examinee.getIdentifier(), 170, 85);
            g.drawString("考生性别：" + (examinee.getSex() ? "男" : "女"), 170, 120);
            g.drawString(String.format("剩余时间：%d分%d秒", time / 60, time % 60), 170, 155);
        }

        public static Image getDefaultExamineeImage() {
            try {
                return ImageIO.read(FileUtils.getFile(LightExam.getInstance().getConfigPath(), "dei.png"));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private QuestionBank bank;
    private Examinee examinee;

    public MainUI(QuestionBank bank, Examinee examinee) {
        this.bank = bank;
        this.examinee = examinee;
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
        JPanel jp = new JPanel(null);
        ExamineeInfoPanel ip = new ExamineeInfoPanel(examinee);
        jp.add(ip);
        ip.setBounds(0, 0, 500, 300);
        add(jp, BorderLayout.CENTER);
        setTitle(bank.getTitle() + " - LightExam");
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
                LightExam.getInstance().getExamination().mark();
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
