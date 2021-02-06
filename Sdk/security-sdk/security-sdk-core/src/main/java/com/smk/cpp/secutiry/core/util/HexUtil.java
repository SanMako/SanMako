/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.secutiry.core.util;

/**
 * @title: HexUtil
 * @Version: V-0.0.1
 * @description: TODO
 */
public class HexUtil {

    public HexUtil() {
    }

    public static String toHex (byte[] bytes, boolean leading0x) {
        StringBuffer hexString = new StringBuffer();
        if (leading0x) {
            hexString.append("0x");
        }
        for (int i = 0; i < bytes.length; i++) {
            int j = bytes[i] & 255;
            String hex = Integer.toHexString(j);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String encode (byte[] bytes, boolean leading0x) {
        return toHex(bytes, leading0x);
    }

    public static byte[] decode (String hexString) {
        return fromHex(hexString);
    }

    public static byte[] fromHex (String hexString) {
        String hexRep = hexString;
        if (hexString.startsWith("0x")) {
            hexRep = hexString.substring(2);
        }
        int len = hexRep.length() / 2;
        byte[] rawBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            String subStr = hexRep.substring(i * 2, i * 2 + 2);
            rawBytes[i] = (byte)Integer.parseInt(subStr, 16);
        }
        return rawBytes;
    }

}
