/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司         */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述:
 *
 * @ClassName: EurekaServiceApplication
 * @Author: Mr.Jin-晋
 * @Date: 2021-01-01 19:21
 * @Version: V1.0
 * @Dedscription:
 */

@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages={"com.smk.cpp"})
public class EurekaServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(EurekaServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
        logger.info("Eureka Service start success !!!!!");
    }

}
