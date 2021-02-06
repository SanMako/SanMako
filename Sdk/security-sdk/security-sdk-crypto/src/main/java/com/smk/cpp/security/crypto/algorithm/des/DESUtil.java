/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.des;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @title: DESUtil
 * @Version: V-0.0.1
 * @description: TODO
 */
public class DESUtil {

    private static final Logger logger = LoggerFactory.getLogger(DESUtil.class);

    private static final String DES_INSTANCE_NAME = "DES/ECB/PKCS5Padding";

    private static final String DESEDE_INSTANCE_NAME = "DESede/ECB/PKCS5Padding";

    private static final int THREE_DES_KEY_SIZE = 168;

    /**
     *
     * @param input
     * @param keyName
     * @return
     * @throws SecurityException
     */
    public static String encryptByDES(String input, String keyName) throws SecurityException {
        if (StringUtils.isEmpty(input) || StringUtils.isEmpty(keyName)) {
            throw new SecurityException("param must not be null");
        }
        try {
            // 加密
            Cipher cipher = Cipher.getInstance(DES_INSTANCE_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyName.getBytes(StandardCharsets.UTF_8),
                    AlgorithmEnum.SIMPLE_DES.getAlgorithmName()));
            byte[] result = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return BASE64Util.encryptByBASE64(result);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptByDES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptByDES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptByDES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptByDES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptByDES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("illegal Block Size Exception！");
        }
    }

    /**
     * des解密
     * @param input
     * @param keyName
     * @return
     * @throws SecurityException
     */

    public static String decryptByDES(String input, String keyName) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance(DES_INSTANCE_NAME);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyName.getBytes(StandardCharsets.UTF_8),
                    AlgorithmEnum.SIMPLE_DES.getAlgorithmName()));
            byte[] result = cipher.doFinal(BASE64Util.decryptByBASE64(input));
            return new String(result, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptByDES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptByDES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptByDES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptByDES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptByDES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("illegal Block Size Exception!");
        }
    }

    /**
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Key getThreeDESKey() throws SecurityException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AlgorithmEnum.SIMPLE_THREE_DES.getAlgorithmName());
            keyGenerator.init(THREE_DES_KEY_SIZE);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("getThreeDESKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        }
    }

    /**
     * 已知密钥串，获取3DES密钥
     * @param key
     * @return
     * @throws Exception
     */
    public static Key getThreeDESKey(String key) throws NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException {
        Provider provider = new BouncyCastleProvider();
        KeySpec keySpec = new DESedeKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
                AlgorithmEnum.SIMPLE_THREE_DES.getAlgorithmName(), provider);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        return secretKey;
    }

    /**
     * 3DES加密
     * @param input
     * @param key
     * @return
     */
    public static String encryptByThreeDES(String input, Key key) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_INSTANCE_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return BASE64Util.encryptByBASE64(result);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptBy3DES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptBy3DES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptBy3DES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptBy3DES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptBy3DES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

    /**
     * 3DES加密
     * @param input
     * @param keyName
     * @return
     */
    public static String encryptByThreeDES(String input, String keyName) throws SecurityException {
        try {
            Key key = getThreeDESKey(keyName);
            return encryptByThreeDES(input, key);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptBy3DES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptBy3DES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("encryptBy3DES ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        }
    }

    /**
     * 3DES解密
     * @param input
     * @param keyName
     * @return
     * @throws SecurityException
     */
    public static String decryptByThreeDES(String input, String keyName) throws SecurityException {
        try {
            Key key = getThreeDESKey(keyName);
            return decryptByThreeDES(input, key);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptBy3DES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptBy3DES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("decryptBy3DES ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        }
    }

    /**
     * 3DES解密
     * @param input
     * @param keyName
     * @return
     * @throws SecurityException
     */
    public static String decryptByThreeDES(String input, Key keyName) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_INSTANCE_NAME);
            cipher.init(Cipher.DECRYPT_MODE, keyName);
            byte[] result = cipher.doFinal(BASE64Util.decryptByBASE64(input));
            return new String(result);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptBy3DES ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptBy3DES ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptBy3DES ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptBy3DES ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptBy3DES ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

}
