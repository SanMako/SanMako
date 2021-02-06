/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.hmac;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @title: HMACUtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class HMACUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(HMACUtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) {
        String key = HMACUtil.initMacKey();
        logger.info("key is ==> {}", key);
        logger.info("result is ==> {}", HMACUtil.encryptByHMAC(testString.getBytes(StandardCharsets.UTF_8), key));
    }

    @Test
    public void test() {
        String inputStr = "西安三码客软件科技有限公司";
        String key = HMACUtil.initMacKey();

        // 验证BASE64加密解密一致性
        Assert.assertEquals(HMACUtil.encryptByHMAC(testString.getBytes(StandardCharsets.UTF_8), key),
                HMACUtil.encryptByHMAC(testString.getBytes(StandardCharsets.UTF_8), key));
    }

}
