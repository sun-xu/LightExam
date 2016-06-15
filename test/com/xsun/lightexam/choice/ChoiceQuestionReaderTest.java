package com.xsun.lightexam.choice;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

/**
 * Created by xsun on 2016/6/14.
 */
public class ChoiceQuestionReaderTest {
    @Test
    public void testReadQuestion() throws Exception {
        ChoiceQuestionReader reader = new ChoiceQuestionReader();
        String json = FileUtils.readFileToString(new File("G:\\LightExam\\res\\choice.json"), "UTF-8");
        ChoiceQuestion question = (ChoiceQuestion) reader.readQuestion(json);
        System.out.println(question);
    }

}