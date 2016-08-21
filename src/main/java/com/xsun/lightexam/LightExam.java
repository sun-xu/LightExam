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

package com.xsun.lightexam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xsun.lightexam.bank.QuestionBank;
import com.xsun.lightexam.bank.QuestionBankReader;
import com.xsun.lightexam.login.Examinee;
import com.xsun.lightexam.login.Login;
import com.xsun.lightexam.util.Utility;
import com.xsun.lightexam.util.Version;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xsun on 2016/6/14.
 */
public class LightExam {

    private QuestionRegistry registry;
    private Examination exam;
    public final static Map<String, String> config;

    public static final LightExam getInstance() {
        return InstanceHolder.INSTANCE;
    }

    static {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String src = null;
        try {
            src = FileUtils.readFileToString(FileUtils.getFile(System.getProperty("user.dir"), "res", "config", "le.json"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (src != null) {
            config = gson.fromJson(src, HashMap.class);
        } else {
            config = new HashMap<>();
        }
    }

    private static class InstanceHolder {
        public static final LightExam INSTANCE = new LightExam();
    }

    public static void main(String[] args) {
        checkVersion();
        Examinee examinee = null;
        try {
            Login login = (Login) Class.forName(config.get("login")).newInstance();
            examinee = login.login();
        } catch (Exception e) {
            System.err.println("登陆出错！");
            e.printStackTrace();
            System.exit(1);
        }
        QuestionBankReader bankReader = new QuestionBankReader();
        QuestionBank bank = null;
        try {
            bank = bankReader.readBank(
                    FileUtils.readFileToString(
                            Utility.getFileInBankDir("bank.json"),
                            "UTF-8"));
        } catch (IOException e) {
            System.err.println("读取题库时出错！");
            e.printStackTrace();
            System.exit(1);
        }
        getInstance().exam = new Examination(bank, examinee);
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
