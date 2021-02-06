/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.algorithm.hash.pbkdf2;

import com.smk.cpp.security.crypto.algorithm.AlgorithmEnum;
import com.smk.cpp.security.crypto.constant.CryptoConstant;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

/**
 * @title: PBKDF2Util
 * @Version: V-0.0.1
 * @description: TODO
 */
public class PBKDF2Util {

    private static final Logger logger = LoggerFactory.getLogger(PBKDF2Util.class);

    public static byte[] encryptByPBKDF2(char[] password, byte[] salt, int iterationNum, int keyLength)
            throws SecurityException {
        try {
            Security.addProvider(new BouncyCastleProvider());
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationNum, keyLength);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
                    AlgorithmEnum.PBKDF2_WITH_HMAC_SHA512.getAlgorithmName(), CryptoConstant.BOUNCY_CASTLE_PROVIDER);
            byte[] bytes = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
            return bytes;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error(noSuchAlgorithmException.getMessage());
            throw new SecurityException("Algorithm is not exist !!!");
        } catch (NoSuchProviderException noSuchProviderException) {
            logger.error(noSuchProviderException.getMessage());
            throw new SecurityException("no such Provider!");
        } catch (InvalidKeySpecException invalidKeySpecException) {
            logger.error(invalidKeySpecException.getMessage());
            throw new SecurityException("key is invalid");
        }
    }

}
