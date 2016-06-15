package com.xsun.lightexam.bank;

import com.xsun.lightexam.api.QuestionBank;
import com.xsun.lightexam.choice.ChoiceQuestion;
import com.xsun.lightexam.choice.ChoiceQuestionReader;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

/**
 * Created by xsun on 2016/6/14.
 */
public class QuestionBankReaderTest {
    @Test
    public void testReadBank() throws Exception {
        QuestionBankReader reader = new QuestionBankReader();
        String json = FileUtils.readFileToString(new File("G:\\LightExam\\res\\bank.json"), "UTF-8");
        QuestionBank bank = reader.readBank(json);
        System.out.println(bank);
    }

}