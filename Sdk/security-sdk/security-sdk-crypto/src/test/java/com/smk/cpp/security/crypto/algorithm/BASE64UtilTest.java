/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm;

import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @title: BASE64UtilTest
 *
 * @Version: V-0.0.1
 * @description: BASE64工具测试类
 */
public class BASE64UtilTest {

    private static final Logger logger = LoggerFactory.getLogger(BASE64UtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) {

        String base64EncryptResult = BASE64Util.encryptByBASE64(testString.getBytes(StandardCharsets.UTF_8));
        logger.info(base64EncryptResult);

        byte  base64DecryptResult[]= BASE64Util.decryptByBASE64(base64EncryptResult);
        String result = new String(base64DecryptResult);
        logger.info(result);

    }

    @Test
    public void test() {
        String inputStr = "西安三码客软件科技有限公司";
        logger.info("密码原文 ==> {}", inputStr);

        String result = BASE64Util.encryptByBASE64(inputStr.getBytes(StandardCharsets.UTF_8));
        logger.info("BASE64加密后 ==> {}", result);

        String outputStr = new String(BASE64Util.decryptByBASE64(result));
        logger.info("BASE64解密后 ==> {}", outputStr);

        // 验证BASE64加密解密一致性
        Assert.assertEquals(inputStr, outputStr);
    }

}
