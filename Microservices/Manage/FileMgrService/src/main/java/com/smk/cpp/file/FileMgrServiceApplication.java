/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述：
 *
 * @ClassName: FileMgrServiceApplication
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-09 22:19
 * @Version: V-0.0.1
 * @Description: TODO
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages={"com.smk.cpp"})
public class FileMgrServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(FileMgrServiceApplication.class);

    public static void main (String[] args) {
        SpringApplication.run(FileMgrServiceApplication.class, args);
        logger.info("file management service start success!!!!!");
    }

}
