/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.common.util.file;

import java.io.File;

/**
 * 功能描述：
 *
 * @ClassName: FileUtils
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 1:39
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class FileUtils {

    /**
     * 创建文件夹
     * @param file
     */
    public static boolean createDir(File file){
        final boolean exists = file.exists();
        if (!exists && !file.mkdirs()) {
            return false;
        }
        return true;
    }

    /**
     * 创建文件夹
     * @param
     */
    public static boolean createDir(String path){
        File file = new File(path);
        final boolean exists = file.exists();
        if (!exists && !file.mkdirs()) {
            return false;
        }
        return true;
    }

}
