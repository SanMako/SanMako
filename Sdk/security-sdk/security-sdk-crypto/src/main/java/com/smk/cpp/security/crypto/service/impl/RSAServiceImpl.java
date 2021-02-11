/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.service.impl;

import com.smk.cpp.security.crypto.algorithm.base64.BASE64Util;
import com.smk.cpp.security.crypto.algorithm.rsa.RSAUtil;
import com.smk.cpp.security.crypto.constant.CryptoConstant;
import com.smk.cpp.security.crypto.service.RSAService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * 功能描述：
 *
 * @ClassName: RSAServiceImpl
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 21:27
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Service
public class RSAServiceImpl implements RSAService {


    @Override
    public String decryptByPrivate(String input) {
        String privateKey = RSAUtil.getPrivateKey(CryptoConstant.PRIVATE_KEY_PATH);
        byte[] bytes = RSAUtil.decryptByPrivateKey(input, privateKey);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String decryptByPublic(String input) {
        String publicKey = RSAUtil.getPublicKey(CryptoConstant.PUBLIC_KEY_PATH);
        byte[] bytes = RSAUtil.decryptByPublicKey(input, publicKey);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String encryptByPrivate(String input) {
        String privateKey = RSAUtil.getPrivateKey(CryptoConstant.PRIVATE_KEY_PATH);
        byte[] bytes = RSAUtil.encryptByPrivateKey(input.getBytes(StandardCharsets.UTF_8), privateKey);
        return BASE64Util.encryptByBASE64(bytes);
    }

    @Override
    public String encryptByPublic(String input) {
        String publicKey = RSAUtil.getPublicKey(CryptoConstant.PUBLIC_KEY_PATH);
        byte[] bytes = RSAUtil.encryptByPublicKey(input, publicKey);
        return BASE64Util.encryptByBASE64(bytes);
    }

    @Override
    public boolean verify(String input) {
        String privateKey = RSAUtil.getPrivateKey(CryptoConstant.PRIVATE_KEY_PATH);
        String publicKey = RSAUtil.getPublicKey(CryptoConstant.PUBLIC_KEY_PATH);
        byte[] bytes = RSAUtil.encryptByPrivateKey(input.getBytes(StandardCharsets.UTF_8), privateKey);
        return RSAUtil.verify(bytes,publicKey,input);
    }

    @Override
    public String sign(String input) {
        String privateKey = RSAUtil.getPrivateKey(CryptoConstant.PRIVATE_KEY_PATH);
        byte[] bytes = RSAUtil.encryptByPrivateKey(input.getBytes(StandardCharsets.UTF_8), privateKey);
        return RSAUtil.sign(bytes,privateKey);
    }

}
