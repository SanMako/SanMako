/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司         */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.eureka.config;

import com.smk.cpp.security.crypto.service.PBKDF2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 功能描述:
 *
 * @ClassName: EurekaPasswordEncoder
 * @Author: Mr.Jin-晋
 * @Date: 2021-01-03 19:11
 * @Version: V1.0
 * @Dedscription:
 */
@Component
public class EurekaPasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(EurekaPasswordEncoder.class);

    @Value("${spring.security.user.salt}")
    private String salt;

    @Value("${spring.security.user.iterNum}")
    private int iterNum;

    @Value("${spring.security.user.key-length}")
    private int keyLength;

    @Autowired
    private PBKDF2Service pbkdf2Service;

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encodedRawPassword = pbkdf2Service.sign(rawPassword.toString(), salt, iterNum, keyLength);
        if (StringUtils.isEmpty(encodedPassword) || StringUtils.isEmpty(encodedRawPassword)) {
            return false;
        }
        return encodedPassword.equals(encodedRawPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

}
