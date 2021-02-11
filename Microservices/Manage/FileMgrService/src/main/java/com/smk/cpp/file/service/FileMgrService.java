/***********************************************************************/
/**            Copyright (C) 2020-2030 西安三码客软件科技有限公司            */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.service;

import com.smk.cpp.file.entity.FileEntity;
import com.smk.cpp.file.exception.FileMgrException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface FileMgrService {

    FileEntity findById(String id);

    /**
     * 删除数据库
     * @param id
     */
    void deleteById(String id);

    void deleteDbAndDiskById(String id);

    void save(FileEntity fileVo) throws FileMgrException;

    void update(FileEntity fileVo);

    List<FileEntity> getListByIds(List<String> fileIds);

    List<String> saveToDbAndDisk(MultipartFile[] files);

}
