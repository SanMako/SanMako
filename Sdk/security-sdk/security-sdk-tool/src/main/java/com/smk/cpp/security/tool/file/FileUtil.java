/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.tool.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 功能描述：
 *
 * @ClassName: FileUtil
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-06 21:56
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取公私钥文件
     *
     * @param path 文件路径
     * @throws Exception 加载公钥时产生的异常
     */
    public static String loadKey(String path) throws SecurityException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new SecurityException("load file error!");
        } catch (NullPointerException e) {
            throw new SecurityException("file is null");
        }
    }

}
