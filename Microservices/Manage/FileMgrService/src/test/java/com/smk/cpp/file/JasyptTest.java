/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file;

import com.smk.cpp.file.resolver.EncryptResolver;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能描述：
 *
 * @ClassName: JasyptTest
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-11 13:09
 * @Version: V-0.0.1
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

    private static final Logger logger = LoggerFactory.getLogger(JasyptTest.class);

    @Autowired
    StringEncryptor stringEncryptor;

    static {
        System.setProperty("jasypt.encryptor.password", "5a1c19cefb2b48ca85b35fff415ada03");
    }

    @Test
    public void testEncrypt() {
//        logger.info(stringEncryptor.encrypt("515322"));
//        Assert.assertEquals("515322", stringEncryptor.decrypt("kvlFy+YAY8zwqLIrEi7wkG1CzAdFcz0rf4T4y6QHnGbF02zdQfCEMSqlzCUXS9JW"));
//        logger.info(stringEncryptor.encrypt("515322"));
//        Assert.assertEquals("515322", stringEncryptor.decrypt("upbPbUuif5cijFB7FPNMgDQv4YIEgLE6KUuD2JGU1tcqsoIvcZLmnoNFAtPN0MRV"));
        EncryptResolver encryptResolver = new EncryptResolver();
    }

    @Autowired
    ConfigurableEnvironment environment;

}
