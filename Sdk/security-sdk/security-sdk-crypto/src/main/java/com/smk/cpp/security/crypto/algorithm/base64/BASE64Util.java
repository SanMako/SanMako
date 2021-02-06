/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 功能描述:
 *
 * @ClassName: BASE64Util
 * @Author: Mr.Jin-晋
 * @Date: 2020-10-31 20:57
 * @Version: V1.0
 * @Dedscription:
 */
public class BASE64Util {

    /**
      * @title: decryptBASE64
      *
      * @param input input
      * @return byte[]
      * @author Mr.Jin-晋
      * @date 2021-01-09 15:26
      * @description: BASE64解码
      */
    public static byte[] decryptByBASE64(String input) {
        return Base64.getDecoder().decode(input);
    }

    /**
     * @title: encryptBASE64
     *
     * @param byte[] input
     * @return String
     * @description: BASE64编码
     */
    public static String encryptByBASE64(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    /**
     * @title: encryptBASE64
     *
     * @param byte[] input
     * @return String
     * @description: BASE64编码
     */
    public static byte[] encryptByBASE64(String input) {
        return Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
    }

}
