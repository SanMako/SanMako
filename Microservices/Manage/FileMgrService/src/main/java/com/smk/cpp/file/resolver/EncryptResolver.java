/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.resolver;

import com.smk.cpp.security.crypto.service.RSAService;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能描述：
 *
 * @ClassName: EncryptResolver
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 20:48
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class EncryptResolver implements EncryptablePropertyResolver {

    @Autowired
    private RSAService rsaService;

    public EncryptResolver() {
    }

    @Override
    public String resolvePropertyValue(String value) {
        if (value != null && value.startsWith("encrypt[")) {
            return rsaService.decryptByPrivate(value.substring("encrypt[".length(), value.length()-1));
        }
        return value;
    }

}
