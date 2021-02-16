/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.manage.common.config;

import com.smk.cpp.manage.common.resolver.EncryptResolver;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 *
 * @ClassName: JasyptConfig
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 20:31
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Configuration
public class JasyptConfig {

    @Bean
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        return new EncryptResolver();
    }

}
