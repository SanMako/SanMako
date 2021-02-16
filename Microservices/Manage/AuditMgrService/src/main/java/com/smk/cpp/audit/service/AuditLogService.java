/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.audit.service;

import com.smk.cpp.security.audit.model.AuditLog;

/**
 * 功能描述：
 *
 * @ClassName: AuditLogSerive
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-15 3:42
 * @Version: V-0.0.1
 * @Description: TODO
 */
public interface AuditLogService {

    void addAuditLog(AuditLog auditLog);

    void updateAuditLog(AuditLog auditLog);

}
