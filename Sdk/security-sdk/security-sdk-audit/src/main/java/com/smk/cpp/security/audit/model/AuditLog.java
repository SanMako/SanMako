/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.audit.model;

import com.smk.cpp.base.enums.ResultEnum;

/**
 * 功能描述：
 *
 * @ClassName: AuditLog
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 23:13
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class AuditLog {

    /**
     * 日志id，自增长
     */
    private String logId;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 操作名称
     */
    private String operateName;

    /**
     * 操作对象
     */
    private String target;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 当前用户请求的requestUrl
     * <p>
     * 如果是http请求，可以带这项
     */
    private String requestUrl;

    /**
     * 请求方式（GET POST PUT DELETE)
     * <p>
     * 如果是http请求，可以带这项
     */
    private String httpMethod;

    /**
     * 危险级别
     */
    private String riskLevel;

    /**
     * 操作结果
     */
    private ResultEnum resultEnum;

    /**
     * http或方法的请求参数体
     */
    private String params;

    private String systemId;

    private String module;

    private String remark;

    /**
     * 当前服务器的ip
     */
    private String serverIp;

    private String clientIp;

    private String operateId;

    private Long createTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
