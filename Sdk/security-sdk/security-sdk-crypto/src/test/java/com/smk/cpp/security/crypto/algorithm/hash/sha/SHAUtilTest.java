/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.sha;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title: SHAUtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class SHAUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(SHAUtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) {
        simpleShaTest();
        sha256Test();
    }

    public static void simpleShaTest () {
        try {
            String shaEncryptResult = SHAUtil.encryptBySHA(testString);
            logger.info(shaEncryptResult);
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
        }
    }

    public static void sha256Test() {
        try {
            String shaEncryptResult = SHAUtil.encryptBySHA256(testString);
            logger.info(shaEncryptResult);
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
        }
    }

    @Test
    public void testSHA() {
        String inputStr = "西安三码客软件科技有限公司";
        // 验证MD5对于同一内容加密是否一致
        Assert.assertEquals(SHAUtil.encryptBySHA(inputStr),
                SHAUtil.encryptBySHA(inputStr));
    }

    @Test
    public void testSHA256() {
        String inputStr = "西安三码客软件科技有限公司";
        // 验证MD5对于同一内容加密是否一致
        Assert.assertEquals(SHAUtil.encryptBySHA256(inputStr),
                SHAUtil.encryptBySHA256(inputStr));
    }

}
