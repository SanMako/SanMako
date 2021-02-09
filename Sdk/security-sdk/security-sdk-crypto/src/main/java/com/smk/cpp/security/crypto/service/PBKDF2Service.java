/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.service;

/**
 * @title: PBKDF2Service
 * @Version: V-0.0.1
 * @description: TODO
 */
public interface PBKDF2Service {

    String sign (String input, String salt, int iterationNum, int keyLength)
            throws SecurityException;

}
