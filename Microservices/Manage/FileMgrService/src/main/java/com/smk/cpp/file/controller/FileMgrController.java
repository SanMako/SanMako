/***********************************************************************/
/**            Copyright (C) 2020-2030 西安三码客软件科技有限公司            */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.file.controller;

import com.smk.cpp.base.model.ResultVo;
import com.smk.cpp.base.controller.BaseController;
import com.smk.cpp.common.util.result.ResultUtil;
import com.smk.cpp.file.entity.FileEntity;
import com.smk.cpp.file.service.FileMgrService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@Api(tags = "文件操作接口")
@RequestMapping("/file/v1")
public class FileMgrController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(FileMgrController.class);

    @Autowired
    private FileMgrService fileMgrService;

    @GetMapping("/{id}")
    public ResultVo<FileEntity> findById(@PathVariable final String id) {
        final FileEntity fileVo = fileMgrService.findById(id);
        return ResultUtil.success(fileVo);
    }

    /**
     * 上传文件
     *
     * @param files
     * @return
     */
    @ResponseBody
    @PostMapping("/upload")
    public ResultVo<String> upload(MultipartFile[] files) {
        List<String> fileIds = fileMgrService.saveToDbAndDisk(files);
        return ResultUtil.success(fileIds);
    }

    /**
     * 保存
     *
     * @param fileVo
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public ResultVo<String> save(@RequestBody FileEntity fileVo) {
        fileMgrService.save(fileVo);
        return ResultUtil.success();
    }

    /**
     * 更新
     *
     * @param fileVo
     * @return
     */
    @ResponseBody
    @PutMapping("/update")
    public ResultVo<String> update(@RequestBody FileEntity fileVo) {
        fileMgrService.update(fileVo);
        return ResultUtil.success();
    }

    /**
     * 删除数据库和磁盘文件
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResultVo<String> deleteAll(@PathVariable final String id) {
        fileMgrService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 只删除数据库
     *
     * @param id
     */
    @DeleteMapping("/rm/{id}")
    public ResultVo<String> deleteDb(@PathVariable final String id) {
        fileMgrService.deleteDbAndDiskById(id);
        return ResultUtil.success();
    }

}