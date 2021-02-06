/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.base.model;

import com.smk.cpp.base.constant.ResultConstants;

import java.util.List;

/**
 * 功能描述: 返回结果集
 *
 * @ClassName: ResultVo
 * @Author: Mr.Jin-晋
 * @Date: 2020-10-14 21:42
 * @Version: V1.0
 * @Dedscription:
 */
public class ResultVo<T> {

    /**
     * 返回码
     */
    private Integer resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;

    /**
     *  数据结果集
     */
    private Result<T> result;

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 当前版本
     */
    private String version = "v1.0";

    public ResultVo() {
        this.result = new Result<>();
    }

    public ResultVo(Integer resultCode, String resultMessage, Result<T> result) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.result = result;
    }

    public ResultVo(Integer resultCode, String resultMessage, Result<T> result, String version) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.result = result;
        this.version = version;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Result<T> getResult() {
        return result;
    }

    public void setResult(Result<T> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return ResultConstants.SUCCESS_CODE == this.resultCode;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 数据结果集
     */
    public static class Result<T> {

        /**
         *  结果条数
         */
        private Integer resultCount;

        /**
         * 数据集
         */
        private List<T> datas;

        public Integer getResultCount() {
            return resultCount;
        }

        public void setResultCount(Integer resultCount) {
            this.resultCount = resultCount;
        }

        public List<T> getDatas() {
            return datas;
        }

        public void setDatas(List<T> datas) {
            this.datas = datas;
        }
    }

}