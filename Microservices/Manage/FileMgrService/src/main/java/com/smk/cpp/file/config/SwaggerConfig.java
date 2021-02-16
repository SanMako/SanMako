/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.config;

import com.smk.cpp.manage.common.config.SwaggerCommonConfig;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述：
 *
 * @ClassName: SwaggerConfig
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-12 18:55
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends SwaggerCommonConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createStandardApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(cppApiInfo())
                .groupName("三码客文件管理微服务规范文档")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 路径使用any风格
                .paths(PathSelectors.any())
                .build()
                .enable(enable);
    }

}
