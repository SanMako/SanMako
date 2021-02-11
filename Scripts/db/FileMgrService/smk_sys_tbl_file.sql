-- ----------------------------
-- Table structure for smk_sys_tbl_file
-- ----------------------------
DROP TABLE IF EXISTS `smk_sys_tbl_file`;
CREATE TABLE `smk_sys_tbl_file` (
`file_id` varchar(64) NOT NULL COMMENT '主键id',
`file_name` varchar(100) NOT NULL COMMENT '文件新名',
`original_file_name` varchar(200) NOT NULL COMMENT '文件原名',
`relatively_path` varchar(200) DEFAULT NULL COMMENT '相对路径',
`file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
`system_id` varchar(64) DEFAULT NULL COMMENT '系统id',
`create_time` bigint DEFAULT NULL COMMENT '创建时间',
`create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
`update_time` bigint DEFAULT NULL COMMENT '更新时间',
`update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件元数据';