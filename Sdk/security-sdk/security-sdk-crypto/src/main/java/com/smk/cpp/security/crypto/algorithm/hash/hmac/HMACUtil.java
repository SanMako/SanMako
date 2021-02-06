/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.hmac;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @title: HMACUtil
 * @Version: V-0.0.1
 * @description: TODO
 */
public class HMACUtil {

    private static final Logger logger = LoggerFactory.getLogger(HMACUtil.class);

    /**
     * 初始化HMAC密钥
     */
    public static String initMacKey() throws SecurityException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AlgorithmEnum.SIMPLE_HMAC_MD5.getAlgorithmName());
            SecretKey secretKey = keyGenerator.generateKey();
            return BASE64Util.encryptByBASE64(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("HMAC_MD5 Algorithm is not exist !!!");
            throw new SecurityException("Algorithm is not exist !!!");
        }
    }

    /**
     * HMAC加密  ：主要方法
     *
     * @param input 待加密文本
     * @param key 密钥别名
     * @return 消息摘要（Base64编码）
     */
    public static String encryptByHMAC(byte[] input, String key) throws SecurityException {

        SecretKey secretKey = new SecretKeySpec(BASE64Util.decryptByBASE64(key),
                AlgorithmEnum.SIMPLE_HMAC_MD5.getAlgorithmName());
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return new String(mac.doFinal(input));
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("HMAC_MD5 Algorithm is not exist !!!");
            throw new SecurityException("Algorithm is not exist !!!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("key ==> {} is in valid", key);
            throw new SecurityException("key is invalid");
        }
    }

}
