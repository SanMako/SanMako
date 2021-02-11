/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.constant;

import com.smk.cpp.base.constant.ResultConstants;

/**
 * 功能描述：
 *
 * @ClassName: ErrorConstant
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 22:29
 * @Version: V-0.0.1
 * @Description: TODO
 */
public interface StatusConstant extends ResultConstants {

    String FILE_SAVE_FAILED = "add.data.error";

    String FILE_UPDATE_FAILED = "update.data.error";

    String RM_FILE_ERROR = "delete.file.error";

    String FILE_NOT_EXISTED = "file.not.exist";

    String UPLOAD_FILE_IS_NULL = "upload.file.is.null";

    String UPLOAD_FILE_ERROR = "upload.file.error";

}
