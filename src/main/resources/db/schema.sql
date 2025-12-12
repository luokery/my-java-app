/*==============================================================*/
/* drop Table: 删除表需要顺序                                   */
/*==============================================================*/

drop table if exists TBL_USER;


/*==============================================================*/
/* Table: TBL_S_USER                                            */
/*==============================================================*/
-- drop table if exists TBL_USER;

create table TBL_USER
(
   USER_NO              char(16) not null comment '用户编号',
   USER_NAME            varchar(50) not null comment '用户名称',
   USER_NICKNAME        varchar(50) not null comment '用户昵称',
   USER_PASSWORD        varchar(50) not null comment '用户密码',
   SALT                 char(8) not null comment '盐巴',
   REC_STATUS           INT(3) not null comment '记录状态',
   CRT_TIME             dateTime not null comment '创建时间',
   CRT_USER             char(32) not null comment '创建人',
   LST_UPD_TIME         dateTime not null comment '最后更新时间',
   LST_UPD_USER         char(32) not null comment '最后更新用户',
   primary key (USER_NO)
);

-- alter table TBL_USER comment '用户表';

alter table TBL_USER add unique AK_uq_TBL_USER_username (USER_NAME);



CREATE TABLE `product` (
	`id` VARCHAR(50) NOT NULL COMMENT '产品编号',
	`name` VARCHAR(255) NOT NULL COMMENT '产品名称',
	`price` DECIMAL(10,2) NOT NULL,
	`stock` INT(10) NOT NULL,
	`create_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

CREATE TABLE `customer` (
	`id` VARCHAR(16) NOT NULL COMMENT '客户编号',
	`username` VARCHAR(255) NOT NULL COMMENT '客户名称',
	`nickname` VARCHAR(255) NULL DEFAULT NULL,
	`email` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`gender` VARCHAR(255) NULL DEFAULT NULL,
	`birth_date` DATE NULL DEFAULT NULL,
	`status` VARCHAR(255) NULL DEFAULT NULL,
	`created_at` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

