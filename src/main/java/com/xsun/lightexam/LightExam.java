package com.xsun.lightexam;

import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.bank.QuestionBankReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by xsun on 2016/6/14.
 */
public class LightExam {

    private QuestionRegistry registry;
    private Examination exam;

    public static final LightExam getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        public static final LightExam INSTANCE = new LightExam();
    }

    public static void main(String[] args) {
        QuestionBankReader bankReader = new QuestionBankReader();
        QuestionBank bank = null;
        try {
            bank = bankReader.readBank(
                    FileUtils.readFileToString(
                            FileUtils.getFile(getInstance().getBankPath(), "bank.json"),
                            "UTF-8"));
        } catch (IOException e) {
            System.err.println("读取题库时出错！");
            e.printStackTrace();
            System.exit(1);
        }
        getInstance().exam = new Examination(bank);
        //TODO: 加入登录系统
        getInstance().exam.start();
    }

    public QuestionRegistry getQuestionRegistry() {
        if (registry == null) {
            try {
                registry = QuestionRegistry.getQuestionRegistryFromJson(FileUtils.readFileToString(
                        FileUtils.getFile(getConfigPath(), "question-registry.json"), "UTF-8"
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return registry;
    }

    public Examination getExamination() {
        return exam;
    }

    public File getConfigPath() {
        return FileUtils.getFile(System.getProperty("user.dir"), "res", "config");
    }

    public File getBankPath() {
        return FileUtils.getFile(System.getProperty("user.dir"), "res",  "bank");
    }
}
