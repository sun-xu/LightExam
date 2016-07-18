package com.xsun.lightexam.textinput.ui;

import com.xsun.lightexam.textinput.TextInputQuestion;

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
            timer.start();
        }

    }

    private class StopButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            jta2.setEditable(false);
            jb2.setEnabled(false);
            jb1.setEnabled(true);
            getQuestion().setYours(jta2.getText());
        }

    }

    private class Timer implements Runnable {

        private int time;
        private Thread t;
        private boolean stop = false;

        public Timer(int time) {
            this.time = time;
            t = new Thread(this);
        }

        @Override
        public void run() {
            while (time > 0) {
                if (stop) {
                    try {
                        synchronized (t) {
                            t.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                --time;
                jl2.setText(formatTime(time));
                try {
                    t.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            jb1.setEnabled(false);
            jb2.setEnabled(false);
            jta2.setEditable(false);
            getQuestion().setYours(jta2.getText());
        }

        public void start() {
            synchronized (t) {
                if (t.getState() == Thread.State.NEW) {
                    t.start();
                } else {
                    stop = false;
                    t.notifyAll();
                }
            }
        }

        public void stop() {
            stop = true;
        }
    }

    private JPanel jp1, jp2;
    private JTextArea jta1, jta2;
    private JButton jb1, jb2;
    private JLabel jl1, jl2;
    private Timer timer;
    private TextInputQuestion question;

    public TextInputQuestionUI(TextInputQuestion question) {
        this.question = question;
        timer = new Timer(question.getTimeLimit());
        initUI();
        update();
        pack();
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
        jl1 = new JLabel("剩余时间:");
        jl2 = new JLabel(formatTime(timer.time));
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jl1);
        jp2.add(jl2);

        add(jp1, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
    }

    private static String formatTime(int time) {
        int second = time % 60;
        int minute = (time - second) / 60;
        return minute + "分" + second + "秒";
    }

    public TextInputQuestion getQuestion() {
        return question;
    }
}
