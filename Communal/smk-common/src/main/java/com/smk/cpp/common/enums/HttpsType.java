/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.common.enums;

import com.smk.cpp.base.enums.EnumTypes;

/**
 * @title: HttpsType
 * @Version: V-0.0.1
 * @description: TODO
 */
public enum HttpsType implements EnumTypes {
    POST_UPPER("POST"),
    GET_UPPER("GET"),
    POST_LOWER("post"),
    GET_LOWER("get"),
    ;

    private String types;

    HttpsType(String types) {
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
