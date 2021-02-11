/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.base.constant;

/**
 * 功能描述:
 *
 * @ClassName: ResultConstants
 * @Author: Mr.Jin-晋
 * @Date: 2020-10-31 20:57
 * @Version: V1.0
 * @Dedscription:
 */
public interface ResultConstants {

    Integer SUCCESS_CODE = 32000;

    Integer INTERNAL_SERVER_ERROR = 35000;

    String OPERATE_SUCCESS = "operate success.";

    String PARAM_CHECK_ERROR = "param.check.error";

    /**
     * 状态码
     * @return
     */
    Integer getStatusCode();

    /**
     * 信息描述
     * @return
     */
    String getMessage();

}
