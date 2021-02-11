/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.exception;

import com.smk.cpp.base.exception.BaseException;

/**
 * 功能描述：
 *
 * @ClassName: FileMgrException
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 22:24
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class FileMgrException extends BaseException {

    public FileMgrException() {
    }

    public FileMgrException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public FileMgrException(String message) {
        super(message);
    }

    public FileMgrException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileMgrException(Throwable cause) {
        super(cause);
    }

    protected FileMgrException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
