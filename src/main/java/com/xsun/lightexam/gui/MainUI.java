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
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                EventQueue.invokeLater(MainUI.this::stopMethod);
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
            JMenu jMenu = new JMenu(bank.getQuestions().get(i).getName());
            jMenu.addMenuListener(new MenuAdapter() {
                @Override
                public void menuSelected(MenuEvent e) {
                    EventQueue.invokeLater(() -> jFrame.setVisible(true));
                }
            });
            jMenuBar.add(jMenu);
        }
        JMenu jMenu = new JMenu("评卷");
        jMenu.addMenuListener(new MenuAdapter() {
            @Override
            public void menuSelected(MenuEvent e) {
                EventQueue.invokeLater(MainUI.this::stopMethod);
            }
        });
        jMenuBar.add(jMenu);
        return jMenuBar;
    }

    private void stopMethod() {
        setVisible(false);
        LightExam.getInstance().getExamination().stop();
    }

    private class MenuAdapter implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {

        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }
}
