/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.md5;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

/**
 * @title: MD5UtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class MD5UtilTest {

    private static final Logger logger = LoggerFactory.getLogger(MD5UtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) {
        try {
            String md5EncryptResult = MD5Util.encryptByMD5(testString);
            logger.info(md5EncryptResult);
        } catch (SecurityException securityException) {
            logger.info(securityException.getMessage());
        }
    }

    @Test
    public void test() {
        String inputStr = "西安三码客软件科技有限公司";
        // 验证MD5对于同一内容加密是否一致
        Assert.assertEquals(MD5Util.encryptByMD5(inputStr),
                MD5Util.encryptByMD5(inputStr));
    }

    @Test
    public void test1() {
        String inputStr = "西安三码客软件科技有限公司";
        String salt = "abcd";
        int hashIterations = 500;
        logger.info(MD5Util.encryptByMD5(inputStr, salt, hashIterations));
        // 验证MD5对于同一内容加密是否一致
        Assert.assertEquals(MD5Util.encryptByMD5(inputStr, salt, hashIterations),
                MD5Util.encryptByMD5(inputStr, salt, hashIterations));
    }

}
