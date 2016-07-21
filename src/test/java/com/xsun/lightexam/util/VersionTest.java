package com.xsun.lightexam.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by xsun on 2016/7/21.
 */
public class VersionTest {
    @Test
    public void testGetVersion() throws Exception {
        System.out.println(Version.getVersion());
    }

}