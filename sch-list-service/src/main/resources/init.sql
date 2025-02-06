DROP TABLE IF EXISTS ui_list_page_info;
CREATE TABLE ui_list_page_info(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `name` VARCHAR(50) NOT NULL  COMMENT '页面名称' ,
    `is_choose` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '页面是否支持多选 0-不支持 1-支持' ,
    `is_col_config` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '列配置是否支持 0-不支持 1-支持' ,
    `is_refresh` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '数据刷新是否支持 0-不支持 1-支持' ,
    `page_version` VARCHAR(20) NOT NULL DEFAULT 'v1' COMMENT '当前页面的版本号' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id)
)  COMMENT = '列表页面信息;';


DROP TABLE IF EXISTS ui_list_data_source;
CREATE TABLE ui_list_data_source(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `select_sql` TEXT   COMMENT '数据查询sql' ,
    `count_sql` TEXT   COMMENT '查询总数sql' ,
    `java_bean` VARCHAR(200)   COMMENT '通过 java_bean的方式构建数据，优先级较低' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间'
)  COMMENT = '列表数据来源;';


DROP TABLE IF EXISTS ui_list_page_field;
CREATE TABLE ui_list_page_field(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `code` VARCHAR(20) NOT NULL  COMMENT '字段code' ,
    `name` VARCHAR(50) NOT NULL  COMMENT '字段名称' ,
    `show_style` VARCHAR(2)   COMMENT '显示格式' ,
    `is_sort` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '是否需要排序 1-需要排序 0-不需要排序' ,
    `show_el` VARCHAR(200) NOT NULL DEFAULT 'val://1' COMMENT '是否展示表达式' ,
    `col_width` VARCHAR(20)   COMMENT '列宽度' ,
    `sort` INT NOT NULL DEFAULT 0 COMMENT '排序' ,
    `val_el` VARCHAR(200)   COMMENT '计算值表达式' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id,code)
)  COMMENT = '列表页面字段信息;';


DROP TABLE IF EXISTS ui_list_page_search_field_relation;
CREATE TABLE ui_list_page_search_field_relation(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `search_code` VARCHAR(20) NOT NULL  COMMENT '搜索code' ,
    `field_code` VARCHAR(20) NOT NULL  COMMENT '字段code' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id,search_code,field_code)
)  COMMENT = '列表页面搜索与字段关系;';

DROP TABLE IF EXISTS ui_list_page_search;
CREATE TABLE ui_list_page_search(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `code` VARCHAR(20) NOT NULL  COMMENT '搜索code' ,
    `name` VARCHAR(50) NOT NULL  COMMENT '搜索名称' ,
    `search_type` VARCHAR(20) NOT NULL DEFAULT 'LIKE' COMMENT '搜索类型' ,
    `web_search_type` VARCHAR(50) NOT NULL DEFAULT '1' COMMENT 'web页面的搜索类型' ,
    `is_many` VARCHAR(10) NOT NULL DEFAULT '1' COMMENT '是否多 0-否 1-是' ,
    `val_el` VARCHAR(200)   COMMENT '如果下拉框之类的，下拉框取值' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id,code)
)  COMMENT = '列表页面搜索;';


DROP TABLE IF EXISTS ui_list_page_btn;
CREATE TABLE ui_list_page_btn(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `code` VARCHAR(20) NOT NULL  COMMENT '按钮code' ,
    `btn_type` VARCHAR(10) NOT NULL DEFAULT '1' COMMENT '按钮类型，1-页面功能按钮 2-页面行数据操作按钮' ,
    `name` VARCHAR(50) NOT NULL  COMMENT '按钮名称' ,
    `sort` INT NOT NULL DEFAULT 0 COMMENT '按钮排序' ,
    `route` VARCHAR(20) NOT NULL  COMMENT '路由' ,
    `show_method` VARCHAR(20) NOT NULL DEFAULT '1' COMMENT '展示方式 1-弹框' ,
    `show_el` VARCHAR(200)  DEFAULT 'val://1' COMMENT '展示 表达式' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id,code)
)  COMMENT = '列表页面操作按钮;';


DROP TABLE IF EXISTS ui_list_page_btn_params;
CREATE TABLE ui_list_page_btn_params(
    `page_id` VARCHAR(50) NOT NULL  COMMENT '页面编号' ,
    `btn_code` VARCHAR(20) NOT NULL  COMMENT '按钮code' ,
    `field_code` VARCHAR(20) NOT NULL  COMMENT '字段code' ,
    `del_flag` VARCHAR(1) NOT NULL  COMMENT '删除标识 1-已经删除 0-未删除' ,
    `create_by` VARCHAR(50) NOT NULL  COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL  COMMENT '创建时间' ,
    `update_by` VARCHAR(50) NOT NULL  COMMENT '更新人' ,
    `update_time` DATETIME NOT NULL  COMMENT '更新时间' ,
    PRIMARY KEY (page_id,btn_code,field_code)
)  COMMENT = '列表页面按钮参数;';


