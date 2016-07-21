package com.xsun.lightexam;

import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.bank.QuestionBankReader;
import com.xsun.lightexam.util.Version;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
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
        checkVersion();
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

    private static void checkVersion() {
        Version version = Version.getVersion();
        StringBuilder builder = new StringBuilder("");
        String versions = version.toString();
        switch (version.type){
            case ALPHA:
                builder.append("当前版本为：").append(versions).append("，属于开发快照版。\n")
                        .append("这说明本软件的主要特性还没有完成，可能会出现重大问题和程序崩溃。\n")
                        .append("请及时汇报问题。请勿将当前版本的软件应用于正式生产环境中！！");
                break;
            case BETA:
                builder.append("当前版本为：").append(versions).append("，属于测试版版。\n")
                        .append("这说明本软件即将进入下一个正式（稳定）版，但仍有可能出现软件漏洞。\n")
                        .append("请及时汇报问题。请谨慎的将当前版本的软件应用于正式生产环境中。");
                break;
        }
        String message;
        if ((message = builder.toString()).isEmpty())
            return;
        JOptionPane.showMessageDialog(null, message, "版本警告", JOptionPane.WARNING_MESSAGE);
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
