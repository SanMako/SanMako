/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.dao;

import com.smk.cpp.file.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: FileMgrDao
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-09 23:47
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Mapper
public interface FileMgrDao {

    FileEntity findById(String id);

    int saveAllFile(List<FileEntity> files);

    int insertFileVo(FileEntity fileVo);

    int deleteById(String id);

    int updateById(FileEntity fileVo);

    List<FileEntity> getFilesByIds(List<String> ids);

}
