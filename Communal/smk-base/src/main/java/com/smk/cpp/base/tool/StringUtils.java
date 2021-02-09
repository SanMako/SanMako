/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.base.tool;

import java.util.UUID;

/**
 * 功能描述:
 *
 * @ClassName: StringUtils
 * @Author: Mr.Jin-晋
 * @Date: 2020-10-31 20:57
 * @Version: V1.0
 * @Dedscription:
 */
public class StringUtils {

    public static final String idGenerate(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static final String uuid(){
        return UUID.randomUUID().toString();
    }

}
