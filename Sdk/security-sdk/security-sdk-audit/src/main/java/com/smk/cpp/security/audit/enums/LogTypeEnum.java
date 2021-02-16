/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.audit.enums;

import com.smk.cpp.base.enums.EnumTypes;

/**
 * 功能描述：
 *
 * @ClassName: LogTypeEnum
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 22:54
 * @Version: V-0.0.1
 * @Description: TODO
 */
public enum LogTypeEnum implements EnumTypes {

    /**
     * 操作日志
     */
    OPERATION ("Operation"),

    /**
     * 安全日志
     */
    SECURITY ("Security"),

    /**
     * 登录日志
     */
    LOGIN ("Login"),
    ;

    private String types;

    LogTypeEnum(String types) {
        this.types = types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String getTypes() {
        return this.types;
    }
}
