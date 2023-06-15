# sql语句
## 1.用户表
CREATE TABLE `user_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50)  DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `register_date` date DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

## 1.订单表
CREATE TABLE `order_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint unsigned NOT NULL COMMENT '用户编号',
  `payment_method` tinyint unsigned NOT NULL COMMENT '支付方式（0：未知，1：账户余额，2：微信支付）',
  `total_money` decimal(10,2) NOT NULL COMMENT '订单总额（单位：元，小数点后2位）',
  `real_money` decimal(10,2) DEFAULT NULL COMMENT '应付金额',
  `discount_money` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_money` decimal(10,2) NOT NULL COMMENT '支付金额（单位：元，小数点后2位）',
  `status` tinyint unsigned NOT NULL COMMENT '订单状态(0:初始中、1:服务中、2:待支付、4、已完成、5：已取消、6:售后中、7:已退费)',
  `type` tinyint unsigned NOT NULL COMMENT '订单类型（1：自主充电）',
  `pay_status` tinyint unsigned NOT NULL COMMENT '订单支付状态（1：待支付，2：已支付）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `success_time` datetime DEFAULT NULL COMMENT '完成时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_account_id_create_time` (`user_id`,`create_time`) USING BTREE,
  KEY `idx_order_type_status` (`type`,`status`),
  KEY `idx_account_seller_type` (`user_id`,`status`,`type`) USING BTREE COMMENT '用于列表查询索引'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='订单信息表';
