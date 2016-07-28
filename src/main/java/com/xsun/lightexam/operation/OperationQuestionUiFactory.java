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

package com.xsun.lightexam.operation;

import com.xsun.lightexam.api.QuestionUiFactory;
import com.xsun.lightexam.operation.ui.OperationQuestionUI;

import javax.swing.*;

/**
 * Created by xsun on 2016/7/24.
 */
public class OperationQuestionUiFactory<Q extends OperationQuestion> implements QuestionUiFactory<Q> {

    @Override
    public JFrame getQuestionUi(Q question) {
        return new OperationQuestionUI(question);
    }

}
