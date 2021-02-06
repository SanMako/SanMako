/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.secutiry.core.exceptions;

import com.smk.cpp.base.exception.BaseException;

/**
 * @title: SecurityException
 *
 * @author Mr.Jin-晋
 * @date 2021-01-09 14:56
 * @Version: V-0.0.1
 * @description: TODO
 */
public class SecurityException extends BaseException {

    public SecurityException() {
    }

    public SecurityException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }

    protected SecurityException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
