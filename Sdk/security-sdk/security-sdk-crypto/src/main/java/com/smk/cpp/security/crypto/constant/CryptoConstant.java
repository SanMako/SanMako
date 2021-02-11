/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.constant;

/**
 * @title: CryptoConstant
 * @Version: V-0.0.1
 * @description: TODO
 */
public interface CryptoConstant {

    String BOUNCY_CASTLE_PROVIDER = "BC";

    String PRIVATE_KEY_PATH = System.getProperty("user.dir") + "/Statics/secret/private_key.pem";

    String PUBLIC_KEY_PATH = System.getProperty("user.dir") + "/Statics/secret/public_key.pem";

}
