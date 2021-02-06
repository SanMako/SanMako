/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.rsa;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import com.smk.cpp.security.tool.file.FileUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 *
 * @ClassName: RSAUtil
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-06 19:20
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final int RSA_ENCRYPT_LENGTH = 2048;

    public static byte[] decryptBASE64(String key) {
        return BASE64Util.decryptByBASE64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return BASE64Util.encryptByBASE64(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws SecurityException {
        try {
            // 解密由base64编码的私钥
            byte[] keyBytes = decryptBASE64(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(AlgorithmEnum.MD5_WITH_RSA.getAlgorithmName());
            signature.initSign(priKey);
            signature.update(data);
            return encryptBASE64(signature.sign());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("sign ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("sign ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("sign ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (SignatureException signatureException) {
            logger.error("sign ==> {}", signatureException.getMessage());
            throw new SecurityException("signature exception!");
        }
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws SecurityException {
        try {
            // 解密由base64编码的公钥
            byte[] keyBytes = decryptBASE64(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(AlgorithmEnum.MD5_WITH_RSA.getAlgorithmName());
            signature.initVerify(pubKey);
            signature.update(data);
            // 验证签名是否正常
            return signature.verify(decryptBASE64(sign));
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("verify ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("verify ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("verify ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (SignatureException signatureException) {
            logger.error("verify ==> {}", signatureException.getMessage());
            throw new SecurityException("signature exception!");
        }
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws SecurityException {
        try{
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptByPrivateKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptByPrivateKey ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptByPrivateKey ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptByPrivateKey ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is bad!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("decryptByPrivateKey ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptByPrivateKey ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key) {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws SecurityException {
        try{
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("decryptByPublicKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("decryptByPublicKey ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("decryptByPublicKey ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("decryptByPublicKey ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is bad!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("decryptByPublicKey ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("decryptByPublicKey ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key) throws SecurityException {
        try{
            // 对公钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptByPublicKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptByPublicKey ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptByPublicKey ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptByPublicKey ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is bad!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("encryptByPublicKey ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptByPublicKey ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws SecurityException {
        try {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("encryptByPrivateKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        } catch (NoSuchPaddingException noSuchPaddingException) {
            logger.error("encryptByPrivateKey ==> {}", noSuchPaddingException.getMessage());
            throw new SecurityException("no such Padding!");
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            logger.error("encryptByPrivateKey ==> {}", illegalBlockSizeException.getMessage());
            throw new SecurityException("key is inValid!");
        } catch (BadPaddingException badPaddingException) {
            logger.error("encryptByPrivateKey ==> {}", badPaddingException.getMessage());
            throw new SecurityException("Padding is bad!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error("encryptByPrivateKey ==> {}", invalidKeySpecException.getMessage());
            throw new SecurityException("invalid Key Spec Exception!");
        } catch (InvalidKeyException invalidKeyException) {
            logger.error("encryptByPrivateKey ==> {}", invalidKeyException.getMessage());
            throw new SecurityException("key is inValid!");
        }
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 从私钥文件取得私钥
     *
     * @param path 私钥文件路径
     * @return
     */
    public static String getPrivateKey(String path) {
        return FileUtil.loadKey(path);
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Key> keyMap) {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 从公钥文件取得公钥
     *
     * @param path 公钥文件路径
     * @return
     */
    public static String getPublicKey(String path) {
        return FileUtil.loadKey(path);
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws SecurityException {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(AlgorithmEnum.RSA.getAlgorithmName());
            keyPairGen.initialize(RSA_ENCRYPT_LENGTH);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            Map<String, Key> keyMap = new HashMap(2);
            keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
            keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
            return keyMap;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("initKey ==> {}", noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist!");
        }
    }

}
