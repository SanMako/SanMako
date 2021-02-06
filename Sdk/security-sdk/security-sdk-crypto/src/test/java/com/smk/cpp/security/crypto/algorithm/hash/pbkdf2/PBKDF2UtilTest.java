/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.pbkdf2;

import com.smk.cpp.security.tool.string.StringTool;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * @title: PBKDF2UtilTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class PBKDF2UtilTest {

    private static final Logger logger = LoggerFactory.getLogger(PBKDF2UtilTest.class);

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        String uuid = "f96948d89fee4bb899f1f9a1934fcb90";
        logger.info(uuid);
        byte[] bytes = PBKDF2Util.encryptByPBKDF2("Smk$@!1121.".toCharArray(),
                uuid.getBytes(StandardCharsets.UTF_8), 1000, 500);
        logger.info(StringTool.HexEncodeTool.byteArrayToHexString(bytes));
    }

}
