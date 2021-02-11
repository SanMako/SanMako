/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.config;

import com.smk.cpp.file.resolver.SmkLocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述：
 *
 * @ClassName: WebMvcConfig
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 22:59
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * 配置自己的国际化语言解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        logger.info("i18n language setting ===>>> LocaleResolver init success!");
        return new SmkLocaleResolver();
    }

}
