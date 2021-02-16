/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.base.enums;

/**
 * 功能描述：
 *
 * @ClassName: ResultEnum
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 23:07
 * @Version: V-0.0.1
 * @Description: TODO
 */
public enum ResultEnum implements EnumTypes {

    SUCCESS ("Success"),
    FAILED ("Failed"),
    ;

    private String types;

    ResultEnum(String types) {
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
