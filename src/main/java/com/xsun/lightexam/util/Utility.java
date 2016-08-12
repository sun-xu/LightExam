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

package com.xsun.lightexam.util;

import com.xsun.lightexam.LightExam;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by xsun on 2016/8/12.
 */
public final class Utility {

    public static File getFileInExamDir(String filename) throws FileNotFoundException {
        if (filename == null) {
            throw new FileNotFoundException("filename was null");
        } else {
            return FileUtils.getFile(LightExam.getInstance().getExamination().getExamDir(), filename);
        }
    }

    public static File getFileInBankDir(String filename) throws FileNotFoundException {
        if (filename == null) {
            throw new FileNotFoundException("filename was null");
        } else {
            return FileUtils.getFile(LightExam.getInstance().getBankPath(), filename);
        }
    }

    public static File getFileInConfigDir(String filename) throws FileNotFoundException {
        if (filename == null) {
            throw new FileNotFoundException("filename was null");
        } else {
            return FileUtils.getFile(LightExam.getInstance().getConfigPath(), filename);
        }
    }

}
