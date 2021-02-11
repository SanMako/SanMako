/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.service;

/**
 * 功能描述：
 *
 * @ClassName: RSAService
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 21:27
 * @Version: V-0.0.1
 * @Description: TODO
 */
public interface RSAService {

    String decryptByPrivate(String input);

    String decryptByPublic(String input);

    String encryptByPrivate(String input);

    String encryptByPublic(String input);

    boolean verify(String input);

    String sign(String input);

}
