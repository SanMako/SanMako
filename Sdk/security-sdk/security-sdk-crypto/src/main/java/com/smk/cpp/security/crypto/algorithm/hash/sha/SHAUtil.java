/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.sha;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title: SHAUtil
 * @Version: V-0.0.1
 * @description: 安全哈希算法（Secure Hash Algorithm）
 * 主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法
 */
public class SHAUtil {

    private static final Logger logger = LoggerFactory.getLogger(SHAUtil.class);

    /**
     * 简单sha1加密 --- 方法禁用
     * @param String input 待加密明文
     * @return 密文
     * @throws SecurityException
     */
    public static String encryptBySHA (String input) throws SecurityException {
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance(AlgorithmEnum.SIMPLE_SHA.getAlgorithmName());
            md.update(input.getBytes(StandardCharsets.UTF_8));
            bigInteger = new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("SHA Algorithm is not exist !!!");
            throw new SecurityException("Algorithm Not Exist");
        }
        return bigInteger.toString(32);
    }

    /**
     * SHA-256
     *
     * @param input 待加密明文
     * @return 密文
     * @throws SecurityException
     */
    public static String encryptBySHA256(String input) throws SecurityException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(AlgorithmEnum.SHA_256.getAlgorithmName());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("SHA-256 Algorithm is not exist !!!");
            throw new SecurityException("Algorithm Not Exist");
        }
        md.update(input.getBytes(StandardCharsets.UTF_8)); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

}
