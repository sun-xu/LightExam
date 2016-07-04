package com.xsun.lightexam;

import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.bank.QuestionBankReader;
import com.xsun.lightexam.gui.MainUI;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by xsun on 2016/6/14.
 */
public class LightExam {

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
        MainUI ui = new MainUI(bank);
        EventQueue.invokeLater(() -> ui.setVisible(true));
    }

    public File getConfigPath() {
        return FileUtils.getFile(System.getProperty("user.dir"), "build", "resources", "main", "config");
    }

    public File getBankPath() {
        return FileUtils.getFile(System.getProperty("user.dir"), "build", "resources", "main", "bank");
    }
}
