package com.xsun.lightexam.gui;

import com.xsun.lightexam.api.QuestionBank;
import com.xsun.lightexam.api.QuestionHolder;
import com.xsun.lightexam.bank.QuestionBankReader;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by xsun on 2016/6/18.
 */
public class MainUI extends JFrame {

    private QuestionBank bank;

    public MainUI(QuestionBank bank){
        this.bank = bank;
        initUI();
    }

    private void initUI() {
        setTitle("Light Exam");
        JMenuBar jMenuBar = new JMenuBar();
        for (QuestionHolder holder : bank.getQuestions())
            jMenuBar.add(createJMenuWithQuestionHolder(holder));
        setJMenuBar(jMenuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JMenu createJMenuWithQuestionHolder(final QuestionHolder holder){
        JMenu jMenu = new JMenu(holder.getName());
        jMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                JFrame jFrame = new JFrame(holder.getName());
                jFrame.setContentPane(holder.getQuestionUi());
                jFrame.pack();
                EventQueue.invokeLater(() -> jFrame.setVisible(true));
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });
        return jMenu;
    }

    public static void main(String[] args) throws IOException{
        QuestionBankReader reader = new QuestionBankReader();
        String json = FileUtils.readFileToString(new File("G:\\LightExam\\res\\bank.json"), "UTF-8");
        QuestionBank bank = reader.readBank(json);
        MainUI ui = new MainUI(bank);
        EventQueue.invokeLater(() -> ui.setVisible(true));
    }
}
