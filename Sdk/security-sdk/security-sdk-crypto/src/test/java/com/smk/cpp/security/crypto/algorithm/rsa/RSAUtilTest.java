/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.rsa;

import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Map;

/**
 * 功能描述：
 *
 * @ClassName: RSAUtilTest
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-06 19:21
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class RSAUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtilTest.class);

    public static void main(String[] args) throws Exception {
        Map<String, Key> keyMap = RSAUtil.initKey();
        String publicKey = RSAUtil.getPublicKey(keyMap);
        String privateKey = RSAUtil.getPrivateKey(keyMap);

        logger.info(String.valueOf(keyMap));
        logger.info("-----------------------------------");
        logger.info(publicKey);
        logger.info("-----------------------------------");
        logger.info(privateKey);
        logger.info("-----------------------------------");
        byte[] encryptByPrivateKey = RSAUtil.encryptByPrivateKey("123456".getBytes(),privateKey);
        byte[] encryptByPublicKey = RSAUtil.encryptByPublicKey("123456",publicKey);
        logger.info(BASE64Util.encryptByBASE64(encryptByPrivateKey));
        logger.info("-----------------------------------");
        logger.info(BASE64Util.encryptByBASE64(encryptByPublicKey));
        logger.info("-----------------------------------");
        String sign = RSAUtil.sign(encryptByPrivateKey,privateKey);
        logger.info(sign);
        logger.info("-----------------------------------");
        boolean verify = RSAUtil.verify(encryptByPrivateKey,publicKey,sign);
        logger.info(String.valueOf(verify));
        logger.info("-----------------------------------");
        byte[] decryptByPublicKey = RSAUtil.decryptByPublicKey(encryptByPrivateKey,publicKey);
        byte[] decryptByPrivateKey = RSAUtil.decryptByPrivateKey(encryptByPublicKey,privateKey);
        logger.info(new String(decryptByPublicKey));
        logger.info("-----------------------------------");
        logger.info(new String(decryptByPrivateKey));

    }

}
