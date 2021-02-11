/***********************************************************************/
/**            Copyright (C) 2020-2030 西安三码客软件科技有限公司            */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = FileProperties.prefix)
public class FileProperties {

    public static final String prefix = "files.upload";

    private String baseDir;

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}