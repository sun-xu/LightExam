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

package com.xsun.lightexam.windowsoperation.checkpoints;

import com.xsun.lightexam.LightExam;
import com.xsun.lightexam.operation.OperationQuestion;
import com.xsun.lightexam.util.AbstractCheckPoint;
import com.xsun.lightexam.windowsoperation.WindowsOperationQuestion;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by xsun on 2016/8/4.
 */
public class CP_WINOP_4 extends AbstractCheckPoint {

    public CP_WINOP_4(String[] data) {
        super(data);
    }

    @Override
    public boolean check(OperationQuestion operationQuestion) {
        File target = FileUtils.getFile(LightExam.getInstance().getExamination().getExamDir(), ((WindowsOperationQuestion) operationQuestion).getSource(), extraData()[0]);
        return !target.exists();
    }
}
