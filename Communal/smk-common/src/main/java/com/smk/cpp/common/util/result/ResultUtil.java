/***********************************************************************/
/**            Copyright (C) 2020-2030 西安三码客软件科技有限公司            */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.common.util.result;

import com.smk.cpp.base.constant.ResultConstants;
import com.smk.cpp.base.exception.BaseException;
import com.smk.cpp.base.model.ResultVo;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 功能描述:
 *
 * @ClassName: ResponseUtil
 * @Author: Mr.Jin-晋
 * @Date: 2020/4/12 19:59
 * @Version: V1.0
 * @Dedscription:
 */
public class ResultUtil {

    /**
     * 成功
     */
    public static ResultVo<String> success(){
        ResultVo<String> vo = new ResultVo<>();
        vo.setSuccess(true);
        vo.setResultCode(ResultConstants.SUCCESS_CODE);
        vo.setResultMessage(ResultConstants.OPERATE_SUCCESS);
        ResultVo.Result<String> result = new ResultVo.Result<>();
        result.setResultCount(0);
        vo.setResult(result);
        return vo;
    }

    /**
     * 返回单个对象
     * @param item
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(T item){
        ResultVo<T> vo = new ResultVo<>();
        vo.setSuccess(true);
        vo.setResultCode(ResultConstants.SUCCESS_CODE);
        vo.setResultMessage(ResultConstants.OPERATE_SUCCESS);
        ResultVo.Result<T> result = new ResultVo.Result<>();
        if (item != null){
            result.setDatas(Collections.singletonList(item));
            result.setResultCount(1);
        } else {
            result.setResultCount(0);
        }
        vo.setResult(result);
        return vo;
    }

    /**
     * 返回list
     * @param items
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(List<T> items){
        ResultVo<T> vo = new ResultVo<>();
        vo.setResultCode(ResultConstants.SUCCESS_CODE);
        vo.setResultMessage(ResultConstants.OPERATE_SUCCESS);
        vo.setSuccess(true);
        ResultVo.Result<T> result = new ResultVo.Result<>();
        if (!CollectionUtils.isEmpty(items)){
            result.setDatas(items);
            result.setResultCount(items.size());
        } else {
            result.setResultCount(0);
        }
        vo.setResult(result);
        return vo;
    }

    public static <T> ResultVo<T> error(Integer code, String message) {
        return error(new BaseException(code, message));
    }

    public static <T> ResultVo<T> error(BaseException e){
        ResultVo<T> vo = new ResultVo<>();
        vo.setResultCode(e.getCode());
        vo.setResultMessage(e.getMessage());
        vo.setSuccess(false);
        return vo;
    }

}
