/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.audit.dao;

import com.smk.cpp.security.audit.model.AuditLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能描述：
 *
 * @ClassName: AuditLogDao
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-15 3:43
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Mapper
public interface AuditLogDao {

    void addAuditLog(AuditLog auditLog);

    void updateAuditLog(AuditLog auditLog);

}
