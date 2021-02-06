/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.des;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @title: DESUtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class DESUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(DESUtilTest.class);

    private static String testString = "SanMako";

    public static void main(String[] args) throws Exception {
        encryptByDES();
        encryptBy3DES();
    }

    private static void encryptByDES() {
        // 生成KEY
        try {
            String key = "smk_keys";
            String result = DESUtil.encryptByDES(testString, key);
            logger.info(result);
            logger.info(DESUtil.decryptByDES(result, key));
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
        }
    }

    private static void encryptBy3DES() {
        // 生成KEY
        try {
            Key desKey = DESUtil.getThreeDESKey();
            String encrypt = new String(Base64.getEncoder().encode(desKey.getEncoded()));
            logger.info("生成的3des秘钥为：" + encrypt);
            String result = DESUtil.encryptByThreeDES(testString, desKey);
            logger.info("生成的3des秘钥加密后的数据：" + result);
            logger.info("java生成3des密钥解密后的数据：" + DESUtil.decryptByThreeDES(result, desKey));
            String key = "LAiSOwt1T2RiXdPT9NYyOJRkYjiYVPiS";
            result = DESUtil.encryptByThreeDES(testString, key);
            logger.info(result);
            logger.info(DESUtil.decryptByThreeDES(result, key));
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
        }
    }

}
