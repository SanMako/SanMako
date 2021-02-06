/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm;

import com.smk.cpp.secutiry.core.algorithm.IAlgorithmEnum;

/**
 * @title: AlgorithmEnum
 * @Version: V-0.0.1
 * @description: TODO
 */
public enum AlgorithmEnum implements IAlgorithmEnum {

    SIMPLE_MD5 ("MD5"),

    SIMPLE_SHA ("SHA"),
    SHA_256 ("SHA-256"),
    SHA_384 ("SHA-384"),
    SHA_512 ("SHA-512"),

    SIMPLE_HMAC_MD5 ("HmacMD5"),
    SIMPLE_HMAC_SHA_256 ("HmacSha256"),
    SIMPLE_HMAC_SHA_384 ("HmacSha384"),
    SIMPLE_HMAC_SHA_512 ("HmacSha512"),

    SIMPLE_AES ("AES"),

    SIMPLE_DES ("DES"),
    SIMPLE_THREE_DES ("DESede"),

    PBE_WITH_MD5_AND_DES ("PBEWITHMD5andDES"),

    PBKDF2_WITH_HMAC_SHA512 ("PBKDF2WithHmacSHA512"),

    RSA ("RSA"),

    MD5_WITH_RSA ("MD5withRSA"),

    ;

    AlgorithmEnum(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    private String algorithmName;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public String getAlgorithmName() {
        return this.algorithmName;
    }

}
