/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import com.smk.cpp.security.crypto.constant.CryptoConstant;
import com.smk.cpp.security.tool.string.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title: AESUtil
 * @Version: V-0.0.1
 * @description: TODO
 */
public class AESUtil {

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private static final String AES_INSTANCE_NAME = "AES/ECB/PKCS5Padding";

    private static final int AES_KEY_SIZE = 128;

    public static String generateAESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AlgorithmEnum.SIMPLE_AES.getAlgorithmName());
            keyGenerator.init(AES_KEY_SIZE);
            // 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获取密钥
            byte[] keyBytes = secretKey.getEncoded();
            return BASE64Util.encryptByBASE64(keyBytes);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("generateAESKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        }
    }

    /**
     * AES加密
     *
     * @param input 待加密明文
     * @param keyName 密钥密钥
     * @return 密文
     * @throws SecurityException
     */
    public static String encryptByAES(String input, String keyName) throws SecurityException {
        try {
            byte[] keyBytes = BASE64Util.decryptByBASE64(keyName);
            // KEY转换
            Key key = new SecretKeySpec(keyBytes, AlgorithmEnum.SIMPLE_AES.getAlgorithmName());
            // 加密
            Cipher cipher = Cipher.getInstance(AES_INSTANCE_NAME, CryptoConstant.BOUNCY_CASTLE_PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(input.getBytes());
            return StringTool.HexEncodeTool.byteArrayToHexString(result);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptByAES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptByAES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptByAES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptByAES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptByAES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchProviderException noSuchProviderException) {
            logger.error("encryptByAES ==> {}", noSuchProviderException.getMessage());
            throw new SecurityException("no such Provider!");
        }
    }

    /**
     * AES解密
     *
     * @param input 待解密明文
     * @param keyName 密钥密钥
     * @return 密文
     * @throws SecurityException
     */
    public static String decryptByAES(String input, String keyName) throws SecurityException {
        try {
            byte[] keyBytes = BASE64Util.decryptByBASE64(keyName);
            Key key = new SecretKeySpec(keyBytes, AlgorithmEnum.SIMPLE_AES.getAlgorithmName());
            Cipher cipher = Cipher.getInstance(AES_INSTANCE_NAME, CryptoConstant.BOUNCY_CASTLE_PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(StringTool.HexEncodeTool.hexStringToByteArray(input));
            return new String(result);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptByAES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptByAES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptByAES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptByAES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptByAES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchProviderException noSuchProviderException) {
            logger.error("decryptByAES ==> {}", noSuchProviderException.getMessage());
            throw new SecurityException("no such Provider!");
        }
    }
}
