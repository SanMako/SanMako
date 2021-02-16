/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.audit.service.impl;

import com.smk.cpp.audit.dao.AuditLogDao;
import com.smk.cpp.audit.service.AuditLogService;
import com.smk.cpp.security.audit.model.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @ClassName: AuditLogServiceImpl
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-15 3:43
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogDao auditLogDao;

    @Override
    public void addAuditLog(AuditLog auditLog) {
        auditLogDao.addAuditLog(auditLog);
    }

    @Override
    public void updateAuditLog(AuditLog auditLog) {
        auditLogDao.updateAuditLog(auditLog);
    }


}
