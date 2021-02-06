/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.pbe;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.tool.string.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @title: PBEUtil
 * @Version: V-0.0.1
 * @description: TODO
 */
public class PBEUtil {

    private static final Logger logger = LoggerFactory.getLogger(PBEUtil.class);

    private static Key getKey(String keyName) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(keyName.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance(AlgorithmEnum.PBE_WITH_MD5_AND_DES.getAlgorithmName());
        return factory.generateSecret(pbeKeySpec);
    }

    /**
     *
     * @param input
     * @param salt
     * @param iterationCount
     * @param keyName
     * @return
     * @throws SecurityException
     */
    public static String encryptByPBE(String input, byte[] salt, int iterationCount, String keyName)
            throws SecurityException {
        try {
            Key key = getKey(keyName);
            PBEParameterSpec pbeParameterSpac = new PBEParameterSpec(salt, iterationCount);//参数：盐和迭代次数
            Cipher cipher = Cipher.getInstance(AlgorithmEnum.PBE_WITH_MD5_AND_DES.getAlgorithmName());
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpac);
            byte[] result = cipher.doFinal(input.getBytes());
            return StringTool.HexEncodeTool.byteArrayToHexString(result);
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("encryptByPBE ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptByPBE ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptByPBE ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptByPBE ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptByPBE ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptByPBE ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            logger.error("encryptByPBE ==> {}", invalidAlgorithmParameterException.getMessage());
            throw new SecurityException("Algorithm Parameter is inValid!");
        }
    }

    /**
     *
     * @param input
     * @param salt
     * @param iterationCount
     * @param keyName
     * @return
     * @throws SecurityException
     */
    public static String decryptByPBE(String input, byte[] salt, int iterationCount, String keyName)
            throws SecurityException {
        try {
            Key key = getKey(keyName);
            PBEParameterSpec pbeParameterSpac = new PBEParameterSpec(salt, iterationCount); // 参数：盐和迭代次数
            Cipher cipher = Cipher.getInstance(AlgorithmEnum.PBE_WITH_MD5_AND_DES.getAlgorithmName());
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpac);
            byte[] result = cipher.doFinal(StringTool.HexEncodeTool.hexStringToByteArray(input));
            return new String(result);
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("decryptByPBE ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptByPBE ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptByPBE ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptByPBE ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptByPBE ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is not good!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptByPBE ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            logger.error("decryptByPBE ==> {}", invalidAlgorithmParameterException.getMessage());
            throw new SecurityException("Algorithm Parameter is inValid!");
        }
    }

}
