package com.xsun.lightexam.gui;

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.api.Question;
import com.xsun.lightexam.api.QuestionMarker;
import com.xsun.lightexam.bank.QuestionBank;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by xsun on 2016/7/18.
 */
public class MarkingUI extends JFrame {

    private QuestionBank bank;
    private JTextArea jta;

    public MarkingUI(QuestionBank bank) {
        this.bank = bank;
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        add(jta, BorderLayout.CENTER);
        setSize(200, 450);
        new BankMarker().execute();
    }

    private class BankMarker extends SwingWorker<Double, Grade> {

        @Override
        protected Double doInBackground() throws Exception {
            return bank.getQuestions().stream().mapToDouble(
                    question -> {
                        Grade g = new Grade();
                        LightExam.getInstance().getQuestionRegistry().getRegistry().stream()
                                .filter(p -> p.getProperty("Question").equals(question.getName()))
                                .map(p -> p.getProperty("Marker"))
                                .findFirst().ifPresent(s -> {
                            try {
                                QuestionMarker marker = (QuestionMarker) Class.forName(s).newInstance();
                                g.grade = marker.mark(question);
                                g.question = question;
                                publish(g);
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                        return g.grade;
                    }
            ).average().getAsDouble();
        }

        @Override
        protected void done() {

        }

        @Override
        protected void process(List<Grade> chunks) {
            chunks.forEach(
                    grade -> {
                        jta.add(String.format("%s得分"))
                    }
            );
        }
    }

    private class Grade {
        Question question;
        double grade;
    }
}
