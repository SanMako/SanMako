/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.md5;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title: MD5Util
 * @Version: V-0.0.1
 * @description: MD5加密 -- 单向加密 Message Digest algorithm 5，信息摘要算法
 * 常用于文件校验
 */
public class MD5Util {

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * @param String input 待加密明文
     * @return 密文
     * @throws SecurityException
     */
    public static String encryptByMD5 (String input) throws SecurityException {
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance(AlgorithmEnum.SIMPLE_MD5.getAlgorithmName());
            md.update(input.getBytes(StandardCharsets.UTF_8));
            bigInteger = new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5 Algorithm is not exist !!!");
            throw new SecurityException("Algorithm Not Exist");
        }
        return bigInteger.toString(16);
    }

    /**
     * @param String input 待加密明文
     * @param salt salt 加密盐
     * @param hashIterations hashIterations 遍历次数
     * @return 密文
     * @throws SecurityException
     */
    public static String encryptByMD5 (String input, String salt, int hashIterations) throws SecurityException {
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance(AlgorithmEnum.SIMPLE_MD5.getAlgorithmName());
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashed = md.digest(input.getBytes(StandardCharsets.UTF_8));
            int iterations = hashIterations - 1;
            for(int i = 0; i < iterations; ++i) {
                md.reset();
                hashed = md.digest(hashed);
            }
            bigInteger = new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5 Algorithm is not exist !!!");
            throw new SecurityException("Algorithm Not Exist");
        }
        return bigInteger.toString(16);
    }

}
