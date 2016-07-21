package com.xsun.lightexam.util;

import java.util.Arrays;

/**
 * Created by xsun on 2016/7/21.
 */
public class Version {

    public enum Type{
        ALPHA,BETA,STABLE;
    }

    private static String version = "0.2.0-ALPHA";

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
