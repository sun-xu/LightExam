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

import javax.swing.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xsun on 2016/8/21.
 */
public class Timer {

    public static class TimerUI extends JLabel {
        public static TimerUI create(Timer timer) {
            TimerUI tui = new TimerUI();
            timer.addListener(s -> {
                tui.setText(String.format("剩余时间：%d分%d秒", s / 60, s % 60));
            });
            tui.setText(String.format("剩余时间：%d分%d秒", timer.time / 60, timer.time % 60));
            return tui;
        }

        private TimerUI() {
            super();
        }
    }

    public interface TimerListener {
        void timeChange(long second);
    }

    private List<TimerListener> listeners = new CopyOnWriteArrayList<>();
    private long time;
    private ScheduledExecutorService service;
    private ExecutorService service1;

    public Timer(long initTime) {
        time = initTime;
    }

    public void run() {
        service = Executors.newScheduledThreadPool(2);
        service1 = Executors.newCachedThreadPool();
        service.scheduleAtFixedRate(() -> {
            if (time == 0)
                stopCountDown();
            else {
                time--;
                listeners.forEach(l -> service1.submit(() -> l.timeChange(time)));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stopCountDown() {
        service.shutdown();
        service1.shutdown();
    }

    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }
}
