/***********************************************************************/
/**            Copyright (C) 2020-2030 西安三码客软件科技有限公司            */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.service.impl;

import com.smk.cpp.base.constant.SymbolConstants;
import com.smk.cpp.base.tool.StringTools;
import com.smk.cpp.common.util.file.FileUtils;
import com.smk.cpp.file.constant.StatusConstant;
import com.smk.cpp.file.dao.FileMgrDao;
import com.smk.cpp.file.entity.FileEntity;
import com.smk.cpp.file.exception.FileMgrException;
import com.smk.cpp.file.properties.FileProperties;
import com.smk.cpp.file.service.FileMgrService;
import com.smk.cpp.manage.common.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileMgrServiceImpl implements FileMgrService {

    private static final Logger logger = LoggerFactory.getLogger(FileMgrServiceImpl.class);

    @Autowired
    private FileMgrDao fileDao;

    @Autowired
    private FileProperties fileProperties;


    @Override
    public FileEntity findById(String id) throws FileMgrException {
        if(StringUtils.isEmpty(id)){
            logger.info("find file by id is null");
            throw new FileMgrException(MessageUtils.getMessage(StatusConstant.PARAM_CHECK_ERROR));
        }
        return fileDao.findById(id);
    }

    private void saveAll(List<FileEntity> fileList) {
        if (fileList.size() > 0) {
            fileDao.saveAllFile(fileList);
        } else {
            logger.info("file list size is 0");
        }
    }

    @Override
    public void deleteById(String id) throws FileMgrException {
        if(StringUtils.isEmpty(id)){
            logger.info("delete file by id is null");
             throw new FileMgrException(MessageUtils.getMessage(StatusConstant.PARAM_CHECK_ERROR));
        }
        if (fileDao.findById(id) == null) {
            logger.error("file is not exist,id is {}", id);
             throw new FileMgrException(MessageUtils.getMessage(StatusConstant.FILE_NOT_EXISTED));
        }
        fileDao.deleteById(id);
    }

    @Override
    public void deleteDbAndDiskById(String id) {
        if(StringUtils.isEmpty(id)){
            logger.info("rm file by id is null");
            throw new FileMgrException(MessageUtils.getMessage(StatusConstant.PARAM_CHECK_ERROR));
        }
        FileEntity fileVo = fileDao.findById(id);
        if (fileVo != null) {
            Path path = Paths.get(fileProperties.getBaseDir(),
//                    fileVo.getSystemId(),
                    "001",
                    fileVo.getRelativelyPath());
            boolean deleted = path.toFile().delete();
            if (deleted) {
                deleteById(id);
            } else {
                logger.error("rm file error,id is " + id);
                throw new FileMgrException(MessageUtils.getMessage(StatusConstant.RM_FILE_ERROR));
            }
        }
    }

    @Override
    public void save(FileEntity fileVo) throws FileMgrException {
        fileVo.setFileId(StringTools.idGenerate());
        int count = fileDao.insertFileVo(fileVo);
        if (count <= 0) {
            throw new FileMgrException(MessageUtils.getMessage(StatusConstant.FILE_SAVE_FAILED));
        }
    }

    @Override
    public void update(FileEntity fileVo) throws FileMgrException {
        if (StringUtils.isEmpty(fileVo.getFileId())) {
            throw new FileMgrException(MessageUtils.getMessage(StatusConstant.PARAM_CHECK_ERROR));
        }
        int count = fileDao.updateById(fileVo);
        if (count <= 0) {
            throw new FileMgrException(MessageUtils.getMessage(StatusConstant.FILE_UPDATE_FAILED));
        }
    }

    @Override
    public List<FileEntity> getListByIds(List<String> fileIds) {
        if (StringUtils.isEmpty(fileIds)) {
            return new ArrayList<>();
        }
        return fileDao.getFilesByIds(fileIds);
    }

    @Override
    public List<String> saveToDbAndDisk(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            logger.info("file list length is 0");
            throw new FileMgrException(StatusConstant.UPLOAD_FILE_IS_NULL);
        }
        List<String> ids = new ArrayList<>();
        FileEntity fileVo = null;
        for (MultipartFile file : files) {
            fileVo = newFile(file);
            fileDao.insertFileVo(fileVo);
            final Path path = Paths.get(
                    fileProperties.getBaseDir(),
//                    fileVo.getSystemId(),
                    "001",
                    fileVo.getRelativelyPath(),
                    fileVo.getFilename());
            final File dir = path.getParent().toFile();
            try {
                FileUtils.createDir(dir);
                file.transferTo(path);
            } catch (IOException e) {
                logger.error("file transfer error,file name is " + file.getOriginalFilename());
                throw new FileMgrException(StatusConstant.UPLOAD_FILE_ERROR);
            }
            ids.add(fileVo.getFileId());
        }
        return ids;
    }

    private FileEntity newFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.indexOf('.')+1);
        String filename = StringTools.uuid();
        String relativelyPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + SymbolConstants.FILE_DIRECTORY_SEPARATOR + type;
        FileEntity fileVo = new FileEntity();
        fileVo.setFileId(StringTools.idGenerate());
        fileVo.setFilename(filename);
        fileVo.setFileType(type);
        fileVo.setOriginalFilename(originalFilename);
        fileVo.setRelativelyPath(relativelyPath);
        return fileVo;
    }

}
