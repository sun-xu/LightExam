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

/**
 * Created by xsun on 2016/7/21.
 */
public class Version {

    public enum Type{
        ALPHA, BETA, STABLE
    }

    private static String version = "0.4.0-ALPHA";

    public final Type type;
    public final int major;
    public final int minor;
    public final int patch;

    private Version(String version){
        String[] strings1 = version.split("-");
        switch (strings1[1].toUpperCase()){
            case "ALPHA":
                type = Type.ALPHA;
                break;
            case "BETA":
                type = Type.BETA;
                break;
            case "STABLE":
            default:
                type = Type.STABLE;
        }
        String[] strings2 = strings1[0].split("\\.");
        major = Integer.parseInt(strings2[0]);
        minor = Integer.parseInt(strings2[1]);
        patch = Integer.parseInt(strings2[2]);
    }

    public static Version getVersion(){
        return new Version(version);
    }

    @Override
    public String toString() {
        return version;
    }
}
