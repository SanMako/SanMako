/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.initialize;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.Security;

/**
 * @title: ContextInitializer
 * @Version: V-0.0.1
 * @description: TODO
 */
public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory.getLogger(ContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Security.addProvider(new BouncyCastleProvider());
    }

}
