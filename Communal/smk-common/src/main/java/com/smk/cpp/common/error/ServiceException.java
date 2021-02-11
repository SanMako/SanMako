/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.common.error;

import com.smk.cpp.base.exception.BaseException;

/**
 * @title: ServiceException
 * @Version: V-0.0.1
 * @description: TODO
 */
public class ServiceException extends BaseException {

    public ServiceException() {
    }

    public ServiceException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
