/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.crypto.service;

import com.smk.cpp.security.crypto.service.impl.PBKDF2ServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: PBKDF2ServiceTest
 * @Version: V-0.0.1
 * @description: TODO
 */
public class PBKDF2ServiceTest {

    @Autowired
    private PBKDF2Service pbkdf2Service;

    @Before
    public void init() {
        pbkdf2Service = new PBKDF2ServiceImpl();
    }

    private static final String testStr = "c11f02a3784dacacd85de4963887c706d9efed1a0ab01001a420230faa504e976f6b40fd754950d848a1066c3f095cfb90391e41d7f0c3df90527df596ab";

    private static final String salt = "f96948d89fee4bb899f1f9a1934fcb90";

    @Test
    public void signTest() {
        String plaintTest = pbkdf2Service.sign("Smk$!1121.", salt, 1000, 500);
        Assert.assertEquals(testStr, plaintTest);
    }

}
