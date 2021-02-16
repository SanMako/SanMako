/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.audit.enums;

import com.smk.cpp.base.enums.EnumTypes;

/**
 * 功能描述：风险级别，其实也可以打分 0-10分，判断风险级别
 *
 * @ClassName: RiskEnums
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 22:40
 * @Version: V-0.0.1
 * @Description: TODO
 */
public enum RiskEnum implements EnumTypes {
    HIGH ("High"),
    MIDDLE ("Middle"),
    LOW ("Low"),
    ;

    private String riskLevel;

    RiskEnum(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String getTypes() {
        return riskLevel;
    }

}
