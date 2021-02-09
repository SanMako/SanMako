/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.service.impl;

import com.smk.cpp.security.crypto.algorithm.hash.pbkdf2.PBKDF2Util;
import com.smk.cpp.security.crypto.service.PBKDF2Service;
import com.smk.cpp.security.tool.string.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @title: PBKDF2ServiceImpl
 * @Version: V-0.0.1
 * @description: TODO
 */
@Service
public class PBKDF2ServiceImpl implements PBKDF2Service {

    private static final Logger logger = LoggerFactory.getLogger(PBKDF2ServiceImpl.class);

    @Override
    public String sign(String input, String salt, int iterationNum, int keyLength) throws SecurityException {
        if (StringUtils.isEmpty(input) || StringUtils.isEmpty(salt) || iterationNum <= 0 || keyLength <= 0) {
            return null;
        }
        try {
            byte[] bytes = PBKDF2Util.encryptByPBKDF2(
                    input.toCharArray(), StringTool.HexEncodeTool.hexStringToByteArray(salt), iterationNum, keyLength);
            return StringTool.HexEncodeTool.byteArrayToHexString(bytes);
        } catch (SecurityException securityException) {
            logger.error(securityException.getMessage());
            throw new SecurityException("sign by PBKDF2 error!!!");
        }

    }

}
