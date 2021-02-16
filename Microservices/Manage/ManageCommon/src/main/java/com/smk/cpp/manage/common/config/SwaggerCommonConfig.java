/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.manage.common.config;

import com.smk.cpp.base.constant.CompanyInfoConstants;
import com.smk.cpp.base.constant.HttpConstants;
import com.smk.cpp.manage.common.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * 功能描述：
 *
 * @ClassName: SwaggerConfig
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 18:55
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class SwaggerCommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerCommonConfig.class);

    protected ApiInfo cppApiInfo(){
        logger.info("swagger ApiInfo inject start!!");
        return new ApiInfoBuilder()
                .title(MessageUtils.getMessage(CompanyInfoConstants.API_INFO_TITLE_KEY))
                .description(MessageUtils.getMessage(CompanyInfoConstants.API_INFO_DESC_KEY))
                .termsOfServiceUrl(HttpConstants.HTTPS + CompanyInfoConstants.SMK_MAIN_DOMAIN)
                .contact(new Contact(
                        CompanyInfoConstants.SMK_FULL_NAME,
                        HttpConstants.HTTPS + CompanyInfoConstants.SMK_MAIN_DOMAIN,
                        CompanyInfoConstants.SMK_CONTACT_EMAIL))
                .version(CompanyInfoConstants.SMK_COMMON_VERSION)
                .build();
    }

}
