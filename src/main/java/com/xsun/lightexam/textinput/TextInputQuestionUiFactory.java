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

package com.xsun.lightexam.textinput;

import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.textinput.ui.TextInputQuestionUI;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/4.
 */
public class TextInputQuestionUiFactory implements QuestionUiFactory<TextInputQuestion> {
    @Override
    public JFrame getQuestionUi(TextInputQuestion question) {
        return new TextInputQuestionUI(question);
    }
}
