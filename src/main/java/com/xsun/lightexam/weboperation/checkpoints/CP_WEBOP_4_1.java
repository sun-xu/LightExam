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

package com.xsun.lightexam.weboperation.checkpoints;

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.operation.OperationQuestion;
import com.xsun.lightexam.util.AbstractCheckPoint;
import com.xsun.lightexam.weboperation.WebOperationQuestion;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created by xsun on 2016/7/30.
 * 下载网页--类型一--全部网页（.html+文件夹）
 */
public class CP_WEBOP_4_1 extends AbstractCheckPoint {

    public CP_WEBOP_4_1(String[] data) {
        super(data);
    }

    @Override
    public boolean check(OperationQuestion operationQuestion) {
        WebOperationQuestion woq = (WebOperationQuestion) operationQuestion;
        File target = FileUtils.getFile(LightExam.getInstance().getExamination().getExamDir(), extraData()[0]);
        File source = FileUtils.getFile(LightExam.getInstance().getExamination().getExamDir(), extraData()[1]);
        Document doc = null;
        try {
            doc = Jsoup.parse(target, "UTF-8", "http://127.0.0.1:8080");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Element body = doc.body();
//        body.equals()
        return false;
    }

    private class ElementComparator implements Comparator<Element> {

        @Override
        public int compare(Element o1, Element o2) {

            return 0;
        }
    }
}
