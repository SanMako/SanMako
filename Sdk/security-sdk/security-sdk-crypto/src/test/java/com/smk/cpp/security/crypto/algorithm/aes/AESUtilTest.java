/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.aes;

import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * @title: AESUtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class AESUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(AESUtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) {
        encryptByAES();
    }

    private static void encryptByAES() {
        // 生成KEY
        try {
            Security.addProvider(new BouncyCastleProvider());
            String key = AESUtil.generateAESKey();
            logger.info("key is ==> {}", key);
            String result = AESUtil.encryptByAES(testString, key);
            logger.info(result);
            logger.info(AESUtil.decryptByAES(result, key));
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
        }
    }

    @Test
    public void test() throws Exception {
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
