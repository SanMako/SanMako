/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.manage.common.exception;

import com.smk.cpp.base.constant.ResultConstants;
import com.smk.cpp.base.exception.BaseException;
import com.smk.cpp.base.model.ResultVo;
import com.smk.cpp.common.util.result.ResultUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能描述：
 *
 * @ClassName: FileMgrExceptionHandler
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 23:59
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Order(-1)
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截业务异常
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVo business(BaseException e) {
        return ResultUtil.error(ResultConstants.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
