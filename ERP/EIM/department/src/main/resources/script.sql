create database if not exists rep_databases;
use rep_databases;


# 公司信息表：公司ID、名称、法律名称、税务登记号、注册日期、类型（如有限责任公司、股份公司等）、行业类型、网站、Logo
# 地址信息表：地址ID、公司ID、地址第一行、城市、州/省、国家、邮政编码
# 联系信息表：公司联系ID、公司ID、联系类型（如总部、分部等）、电话号码、传真号码、电子邮件
# 服务信息表：服务ID、服务名称、服务代码、单价

-- 创建公司信息表
CREATE TABLE IF NOT EXISTS `company_info` (
    `company_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '公司ID',
    `name` VARCHAR(64) NOT NULL COMMENT '名称',
    `legal_name` VARCHAR(255) NOT NULL COMMENT '法律名称',
    `tax_registration_number` VARCHAR(128) NOT NULL COMMENT '税务登记号',
    `registration_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
    `type` VARCHAR(16) NOT NULL COMMENT '类型（如有限责任公司、股份公司等）',
    `industry_type` VARCHAR(32) NOT NULL COMMENT '行业类型',
    `website` VARCHAR(128) COMMENT '网站',
    `logo` VARCHAR(255) COMMENT 'Logo',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='公司信息表';

-- 创建地址信息表
CREATE TABLE IF NOT EXISTS `address_info` (
    `address_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '地址ID',
    `company_id` INT NOT NULL COMMENT '公司ID',
    `address_line` VARCHAR(255) NOT NULL COMMENT '地址',
    `city` VARCHAR(32) NOT NULL COMMENT '城市',
    `state_province` VARCHAR(32) NOT NULL COMMENT '州/省',
    `country` VARCHAR(32) NOT NULL COMMENT '国家',
    `postal_code` VARCHAR(64) NOT NULL COMMENT '邮政编码',
    FOREIGN KEY (`company_id`) REFERENCES `company_info`(`company_id`)
) COMMENT='地址信息表';

-- 创建联系信息表
CREATE TABLE IF NOT EXISTS `contact_info` (
    `contact_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '公司联系ID',
    `company_id` INT NOT NULL COMMENT '公司ID',
    `contact_type` VARCHAR(8) NOT NULL COMMENT '联系类型（如总部、分部等）',
    `phone_number` VARCHAR(15) NOT NULL COMMENT '电话号码',
    `fax_number` VARCHAR(32) COMMENT '传真号码',
    `email` VARCHAR(64) NOT NULL COMMENT '电子邮件',
    FOREIGN KEY (`company_id`) REFERENCES `company_info`(`company_id`)
) COMMENT='联系信息表';

-- 创建服务信息表
CREATE TABLE IF NOT EXISTS `service_info` (
    `service_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '服务ID',
    `service_name` VARCHAR(32) NOT NULL COMMENT '服务名称',
    `service_code` VARCHAR(16) NOT NULL COMMENT '服务代码',
    `unit_price` DECIMAL(10, 2) NOT NULL COMMENT '单价',
    `company_id` INT NOT NULL COMMENT '公司ID',
    FOREIGN KEY (`company_id`) REFERENCES `company_info`(`company_id`)
) COMMENT='服务信息表';




# 创建用户表
CREATE TABLE if not exists `user` (
    `user_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(32) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `email` VARCHAR(32) COMMENT '电子邮箱',
    `phone` VARCHAR(11) COMMENT '联系电话',
    `first_name` VARCHAR(12) COMMENT '用户名字',
    `last_name` VARCHAR(6) COMMENT '用户姓氏',
    `is_active` INT DEFAULT 0 COMMENT '激活状态',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_at` DATETIME COMMENT '最后登录时间',
    `company_id` INT COMMENT '公司ID',
    FOREIGN KEY (`company_id`) REFERENCES `company_info`(`company_id`)
);

# 创建用户会话表
CREATE TABLE if not exists `USER_SESSION_TABLE` (
    `session_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '会话ID',
    `user_id` INT NOT NULL COMMENT '用户ID',
    `start_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '会话开始时间',
    `end_time` DATETIME COMMENT '会话结束时间',
    `login_ip` VARCHAR(15) COMMENT '登录IP地址',
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);

# 创建审计日志表
CREATE TABLE if not exists `USER_AUDIT_LOG_TABLE` (
    `log_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
    `user_id` INT NOT NULL COMMENT '用户ID',
    `operation` VARCHAR(32) NOT NULL COMMENT '操作',
    `operation_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `operation_info` VARCHAR(255) COMMENT '操作信息',
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);

# 创建角色表
CREATE TABLE if not exists `ROLE_TABLE` (
    `role_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(32) NOT NULL COMMENT '角色名称',
    `role_desc` VARCHAR(255) COMMENT '角色描述'
);

# 创建用户角色映射表
CREATE TABLE if not exists `USER_ROLE_MAPPING_TABLE` (
    `user_id` INT NOT NULL COMMENT '用户ID',
    `role_id` INT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
    FOREIGN KEY (`role_id`) REFERENCES `role_table`(`role_id`)
);


# 创建权限表
CREATE TABLE if not exists `PERMISSION_TABLE` (
    `permission_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '权限ID',
    `permission_name` VARCHAR(32) NOT NULL COMMENT '权限名称',
    `permission_desc` VARCHAR(255) COMMENT '权限描述'
);


# 创建角色权限映射表
CREATE TABLE if not exists `ROLE_PERMISSION_MAPPING_TABLE` (
    `role_id` INT NOT NULL COMMENT '角色ID',
    `permission_id` INT NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`role_id`, `permission_id`),
    FOREIGN KEY (`role_id`) REFERENCES `role_table`(`role_id`),
    FOREIGN KEY (`permission_id`) REFERENCES `permission_table`(`permission_id`)
);


-- 创建部门表
CREATE TABLE IF NOT EXISTS `DEPARTMENT_TABLE` (
    `department_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    `department_name` VARCHAR(32) NOT NULL COMMENT '部门名称',
    `company_contact_id` INT COMMENT '公司联系ID',
    `parent_department_id` INT COMMENT '上级部门',
    `department_desc` VARCHAR(255) COMMENT '部门描述',
    FOREIGN KEY (`company_contact_id`) REFERENCES `contact_info`(`contact_id`)
) COMMENT='部门表';



# 创建部门预算表
CREATE TABLE if not exists `DEPARTMENT_BUDGET_TABLE` (
    `budget_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '预算ID',
    `department_id` INT NOT NULL COMMENT '部门ID',
    `financial_year` VARCHAR(10) NOT NULL COMMENT '财政年度',
    `total_budget` DECIMAL(10, 2) NOT NULL COMMENT '预算总额',
    `spent_budget` DECIMAL(10, 2) NOT NULL COMMENT '已花费金额',
    `remaining_budget` DECIMAL(10, 2) NOT NULL COMMENT '剩余金额',
    FOREIGN KEY (`department_id`) REFERENCES `department_table`(`department_id`)
);

# 创建项目表
CREATE TABLE if not exists `PROJECT_TABLE` (
    `project_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '项目ID',
    `project_name` VARCHAR(32) NOT null COMMENT '项目名称',
    `start_date` DATE NOT NULL COMMENT '项目开始日期',
    `end_date` DATE NOT NULL COMMENT '项目预计结束日期',
    `project_desc` VARCHAR(255) COMMENT '项目描述'
);

# 岗位表：岗位ID、岗位名称、岗位代码、部门ID、职责描述、薪资范围、教育背景要求、工作经验要求、岗位状态（开放、关闭、招聘中）
CREATE TABLE if not exists `POSITION_TABLE` (
    `position_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '岗位ID',
    `position_name` VARCHAR(32) NOT NULL COMMENT '岗位名称',
    `position_code` VARCHAR(10) NOT NULL COMMENT '岗位代码',
    `department_id` INT NOT NULL COMMENT '部门ID',
    `responsibility_desc` VARCHAR(255) COMMENT '职责描述',
    `salary_range` VARCHAR(32) COMMENT '薪资范围',
    `education_background_requirement` VARBINARY(255) COMMENT '教育背景要求',
    `work_experience_requirement` VARCHAR(32) COMMENT '工作经验要求',
    `position_status` VARCHAR(10) NOT NULL COMMENT '岗位状态（开放、关闭、招聘中）',
    FOREIGN KEY (`department_id`) REFERENCES `department_table`(`department_id`)
);

# 员工表：员工ID、名字、姓氏、职位、电子邮箱、联系电话、部门ID、雇佣日期、当前薪资、员工状态（在职、离职、休假）
CREATE TABLE if not exists `EMPLOYEE_TABLE` (
    `employee_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工ID',
    `first_name` VARCHAR(12) NOT NULL COMMENT '名字',
    `last_name` VARCHAR(6) NOT NULL COMMENT '姓氏',
    `position_id` INT NOT NULL COMMENT '职位',
    `email` VARBINARY(32) COMMENT '电子邮箱',
    `phone` VARCHAR(11) COMMENT '联系电话',
    `department_id` INT NOT NULL COMMENT '部门ID',
    `hire_date` DATE NOT NULL COMMENT '雇佣日期',
    `current_salary` DECIMAL(12, 2) NOT NULL COMMENT '当前薪资',
    `employee_status` VARCHAR(10) NOT NULL COMMENT '员工状态（在职、离职、休假）',
    FOREIGN KEY (`position_id`) REFERENCES `position_table`(`position_id`),
    FOREIGN KEY (`department_id`) REFERENCES `department_table`(`department_id`)
);

# 技能表：技能ID、技能名称、技能描述
CREATE TABLE if not exists `SKILL_TABLE` (
    `skill_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '技能ID',
    `skill_name` VARCHAR(32) NOT NULL COMMENT '技能名称',
    `skill_desc` VARCHAR(255) COMMENT '技能描述'
);

# 员工技能映射表：员工ID、技能ID、技能熟练度
CREATE TABLE if not exists `EMPLOYEE_SKILL_MAPPING_TABLE` (
    `employee_id` INT NOT NULL COMMENT '员工ID',
    `skill_id` INT NOT NULL COMMENT '技能ID',
    `skill_level` INT NOT NULL COMMENT '技能熟练度',
    PRIMARY KEY (`employee_id`, `skill_id`),
    FOREIGN KEY (`employee_id`) REFERENCES `employee_table`(`employee_id`),
    FOREIGN KEY (`skill_id`) REFERENCES `skill_table`(`skill_id`)
);

# 资格表：资格ID、资格名称、资格描述
CREATE TABLE if not exists `QUALIFICATION_TABLE` (
    `qualification_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '资格ID',
    `qualification_name` VARCHAR(32) NOT NULL COMMENT '资格名称',
    `qualification_desc` VARCHAR(255) COMMENT '资格描述'
);

# 岗位资格映射表：岗位ID、资格ID
CREATE TABLE if not exists `POSITION_QUALIFICATION_MAPPING_TABLE` (
    `position_id` INT NOT NULL COMMENT '岗位ID',
    `qualification_id` INT NOT NULL COMMENT '资格ID',
    PRIMARY KEY (`position_id`, `qualification_id`),
    FOREIGN KEY (`position_id`) REFERENCES `position_table`(`position_id`),
    FOREIGN KEY (`qualification_id`) REFERENCES `qualification_table`(`qualification_id`)
);

# 岗位变动表：岗位变动ID、岗位ID、员工ID、变动时间、变动原因、变动前岗位、变动后岗位、变动前薪资、变动后薪资
CREATE TABLE if not exists `POSITION_CHANGE_TABLE` (
    `position_change_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '岗位变动ID',
    `position_id` INT NOT NULL COMMENT '岗位ID',
    `employee_id` INT NOT NULL COMMENT '员工ID',
    `change_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '变动时间',
    `change_reason` VARCHAR(255) COMMENT '变动原因',
    `before_position` VARCHAR(32) COMMENT '变动前岗位',
    `after_position` VARCHAR(32) COMMENT '变动后岗位',
    `before_salary` DECIMAL(12, 2) COMMENT '变动前薪资',
    `after_salary` DECIMAL(12, 2) COMMENT '变动后薪资',
    FOREIGN KEY (`position_id`) REFERENCES `position_table`(`position_id`),
    FOREIGN KEY (`employee_id`) REFERENCES `employee_table`(`employee_id`)
);


# 创建客户基本表
CREATE TABLE if not exists `CUSTOMER_TABLE` (
    `customer_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '客户编号',
    `customer_name` VARCHAR(32) NOT NULL COMMENT '客户名称',
    `address` VARCHAR(255) COMMENT '地址',
    `contact_info` VARCHAR(32) COMMENT '联系方式',
    `tax_info` VARCHAR(255) COMMENT '税务信息',
    `bank_account` VARCHAR(32) COMMENT '银行账户',
    `credit_code` VARCHAR(32) COMMENT '信用编号',
    `category_code` VARCHAR(32) COMMENT '分类编号',
    `trade_code` VARCHAR(32) COMMENT '交易编号',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);

# 创建客户信用表
CREATE TABLE if not exists `CUSTOMER_CREDIT_TABLE` (
    `credit_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '信用编号',
    `credit_amount` DECIMAL(12, 2) NOT NULL COMMENT '信用额度',
    `credit_term` INT NOT NULL COMMENT '信用期限',
    `payment_method` VARCHAR(32) NOT NULL COMMENT '支付方式'
);

# 创建客户分类表
CREATE TABLE if not exists `CUSTOMER_CATEGORY_TABLE` (
    `category_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类编号',
    `industry` VARCHAR(32) NOT NULL COMMENT '行业',
    `region` VARCHAR(32) NOT NULL COMMENT '地区',
    `level` INT NOT NULL COMMENT '等级'
);

# 创建客户交易表
CREATE TABLE if not exists `CUSTOMER_TRADE_TABLE` (
    `trade_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '交易编号',
    `product_name` VARCHAR(32) NOT NULL COMMENT '购买的产品',
    `quantity` INT NOT NULL COMMENT '数量',
    `price` DECIMAL(12, 2) NOT NULL COMMENT '价格',
    `payment_status` VARCHAR(32) NOT NULL COMMENT '付款情况'
);


# 创建供应商基本表
CREATE TABLE if not exists `SUPPLIER_TABLE` (
    `supplier_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '供应商编号',
    `supplier_name` VARCHAR(32) NOT NULL COMMENT '供应商名称',
    `contact_person` VARCHAR(32) NOT NULL COMMENT '联系人',
    `contact_info` VARCHAR(32) NOT NULL COMMENT '联系方式',
    `email` VARCHAR(32) COMMENT '电子邮箱',
    `address` VARCHAR(255) NOT NULL COMMENT '地址',
    `status` VARCHAR(10) NOT NULL COMMENT '状态（活跃、暂停、黑名单）',
    `contract_expiry_date` DATE NOT NULL COMMENT '合同到期日期',
    `credit_amount` DECIMAL(12, 2) NOT NULL COMMENT '信用额度',
    `payment_terms` VARCHAR(255) NOT NULL COMMENT '付款条款',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);

# 创建供应商合同表
CREATE TABLE if not exists `SUPPLIER_CONTRACT_TABLE` (
    `contract_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '合同ID',
    `contract_no` VARCHAR(32) NOT NULL COMMENT '合同编号',
    `supplier_id` INT NOT NULL COMMENT '供应商基本表',
    `start_date` DATE NOT NULL COMMENT '合同开始日期',
    `end_date` DATE NOT NULL COMMENT '合同结束日期',
    `contract_value` DECIMAL(12, 2) NOT NULL COMMENT '合同价值'
);

# 创建供应商绩效表
CREATE TABLE if not exists `SUPPLIER_PERFORMANCE_TABLE` (
    `performance_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '绩效ID',
    `supplier_id` INT NOT NULL COMMENT '供应商基本表',
    `start_date` DATE NOT NULL COMMENT '绩效评估开始日期',
    `end_date` DATE NOT NULL COMMENT '绩效评估结束日期',
    `on_time_delivery_rate` DECIMAL(5, 2) NOT NULL COMMENT '准时交货率',
    `quality_failure_rate` DECIMAL(5, 2) NOT NULL COMMENT '质量失败率',
    `cost_performance_index` DECIMAL(5, 2) NOT NULL COMMENT '成本绩效指数'
);


# 创建商品基本信息表
CREATE TABLE if not exists `PRODUCT_TABLE` (
    `product_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '商品编号',
    `product_name` VARCHAR(32) NOT NULL COMMENT '商品名称',
    `brand` VARCHAR(32) NOT NULL COMMENT '品牌',
    `spec` VARCHAR(32) NOT NULL COMMENT '规格',
    `model` VARCHAR(32) NOT NULL COMMENT '型号',
    `packing_unit` VARCHAR(32) NOT NULL COMMENT '包装单位',
    `shelf_life` INT NOT NULL COMMENT '商品保质期',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);


# 创建商品价格信息表
CREATE TABLE if not exists `PRODUCT_PRICE_TABLE` (
    `price_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '商品价格编号',
    `market_price` DECIMAL(12, 2) NOT NULL COMMENT '市场价格',
    `purchase_cost` DECIMAL(12, 2) NOT NULL COMMENT '采购成本',
    `sale_price` DECIMAL(12, 2) NOT NULL COMMENT '销售价格',
    `profit` DECIMAL(12, 2) NOT NULL COMMENT '利润'
);


# 创建销售订单表
CREATE TABLE if not exists `SALE_ORDER_TABLE` (
    `order_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单编号',
    `customer_id` INT NOT NULL COMMENT '客户编号',
    `order_date` DATE NOT NULL COMMENT '订单日期',
    `delivery_address` VARCHAR(255) NOT NULL COMMENT '发货地址',
    `order_status` VARCHAR(10) NOT NULL COMMENT '订单状态（新订单、已发货、已完成）'
);


# 创建销售订单详细表
CREATE TABLE if not exists `SALE_ORDER_DETAIL_TABLE` (
    `detail_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单详细编号',
    `order_id` INT NOT NULL COMMENT '订单编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `quantity` INT NOT NULL COMMENT '销售数量',
    `unit_price` DECIMAL(12, 2) NOT NULL COMMENT '销售单价',
    `total_amount` DECIMAL(12, 2) NOT NULL COMMENT '销售总金额'
);


# 创建发票表
CREATE TABLE if not exists `INVOICE_TABLE` (
    `invoice_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '发票编号',
    `order_id` INT NOT NULL COMMENT '订单编号',
    `invoice_date` DATE NOT NULL COMMENT '发票日期',
    `expiry_date` DATE NOT NULL COMMENT '到期日期',
    `total_amount` DECIMAL(12, 2) NOT NULL COMMENT '总金额'
);


# 创建收款表
CREATE TABLE if not exists `PAYMENT_TABLE` (
    `payment_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '收款编号',
    `invoice_id` INT NOT NULL COMMENT '发票编号',
    `payment_date` DATE NOT NULL COMMENT '收款日期',
    `amount` DECIMAL(12, 2) NOT NULL COMMENT '收款金额'
);


# 创建退货单
CREATE TABLE if not exists `RETURN_ORDER_TABLE` (
    `return_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '退货编号',
    `order_id` INT NOT NULL COMMENT '订单编号',
    `return_price` DECIMAL(12, 2) NOT NULL COMMENT '应退价格',
    `refund_amount` DECIMAL(12, 2) NOT NULL COMMENT '退款金额',
    `reason` VARCHAR(255) NOT NULL COMMENT '退货原因',
    `return_status` VARCHAR(10) NOT NULL COMMENT '退货状态（未入库、已入库）',
    `refund_status` VARCHAR(10) NOT NULL COMMENT '退款状态（正在处理、已退款）',
    `refund_date` DATE NOT NULL COMMENT '退款日期',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);

# 创建仓库表
# 仓库基本信息：仓库编号、仓库名称、仓库代码、地址、负责人、类型、特殊要求
CREATE TABLE if not exists `WAREHOUSE_TABLE`
(
    `warehouse_id`   INT PRIMARY KEY AUTO_INCREMENT COMMENT '仓库编号',
    `warehouse_name` VARCHAR(32)  NOT NULL COMMENT '仓库名称',
    `warehouse_code` VARCHAR(10)  NOT NULL COMMENT '仓库代码',
    `address`        VARCHAR(255) NOT NULL COMMENT '地址',
    `manager`        VARCHAR(32)  NOT NULL COMMENT '负责人',
    `special_request` VARCHAR(255) NOT NULL COMMENT '特殊要求'
);

# 创建采购订单表
CREATE TABLE if not exists `PURCHASE_ORDER_TABLE` (
    `order_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    `supplier_id` INT NOT NULL COMMENT '供应商编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `order_date` DATE NOT NULL COMMENT '订单日期',
    `delivery_date` DATE NOT NULL COMMENT '预计交付日期',
    `receive_date` DATE NOT NULL COMMENT '实际接收日期',
    `quantity` INT NOT NULL COMMENT '订单数量',
    `unit_price` DECIMAL(12, 2) NOT NULL COMMENT '单价',
    `total_price` DECIMAL(12, 2) NOT NULL COMMENT '总价格',
    `order_status` VARCHAR(10) NOT NULL COMMENT '订单状态（待发货、已发货、已接收）'
);


# 创建采购支付表
CREATE TABLE if not exists `PURCHASE_PAYMENT_TABLE` (
    `payment_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '支付ID',
    `order_id` INT NOT NULL COMMENT '订单ID',
    `payment_date` DATE NOT NULL COMMENT '支付日期',
    `amount` DECIMAL(12, 2) NOT NULL COMMENT '支付金额',
    `payment_method` VARCHAR(32) NOT NULL COMMENT '支付方式'
);


# 创建质量检测表
CREATE TABLE if not exists `QUALITY_CHECK_TABLE` (
    `check_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '质检ID',
    `order_id` INT NOT NULL COMMENT '订单ID',
    `check_date` DATE NOT NULL COMMENT '检查日期',
    `check_result` VARCHAR(10) NOT NULL COMMENT '检查结果（合格、不合格）',
    `check_remark` VARCHAR(255) NOT NULL COMMENT '检查备注'
);


# 创建库存表
CREATE TABLE if not exists `STOCK_TABLE` (
    `stock_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '库存编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `product_type` VARCHAR(32) NOT NULL COMMENT '商品类型',
    `location` VARCHAR(32) NOT NULL COMMENT '库存位置',
    `quantity` INT NOT NULL COMMENT '库存数量',
    `in_time` DATE NOT NULL COMMENT '入库时间',
    `out_time` DATETIME COMMENT '出库时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);




# 创建商品出库基本信息表
CREATE TABLE if not exists `OUT_ORDER_TABLE` (
    `out_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '出库编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `reason` VARCHAR(255) NOT NULL COMMENT '出库原因',
    `quantity` INT NOT NULL COMMENT '出库数量',
    `transport_method` VARCHAR(32) NOT NULL COMMENT '运输方式',
    `out_time` DATE NOT NULL COMMENT '出库时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);


# 创建商品出库明细表
CREATE TABLE if not exists `OUT_ORDER_DETAIL_TABLE` (
    `out_detail_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '出库明细ID',
    `out_id` INT NOT NULL COMMENT '出库编号',
    `stock_id` INT NOT NULL COMMENT '库存编号',
    `quantity` INT NOT NULL COMMENT '出库数量',
    `unit_price` DECIMAL(12, 2) NOT NULL COMMENT '出库单价',
    `total_price` DECIMAL(12, 2) NOT NULL COMMENT '出库总价'
);



# 创建破损基本表
CREATE TABLE if not exists `DAMAGED_TABLE` (
    `damaged_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '破损编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `supplier_id` INT NOT NULL COMMENT '供应商编号',
    `stock_id` INT NOT NULL COMMENT '库存编号',
    `quantity` INT NOT NULL COMMENT '破损数量',
    `reason` VARCHAR(255) NOT NULL COMMENT '破损原因',
    `damaged_time` DATE NOT NULL COMMENT '破损时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);


# 创建溢出基本表
CREATE TABLE if not exists `OVERFLOW_TABLE` (
    `overflow_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '溢出编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `stock_id` INT NOT NULL COMMENT '库存编号',
    `quantity` INT NOT NULL COMMENT '溢出数量',
    `reason` VARCHAR(255) NOT NULL COMMENT '溢出原因',
    `overflow_time` DATE NOT NULL COMMENT '溢出时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);


# 创建库存报警表
CREATE TABLE if not exists `STOCK_ALARM_TABLE` (
    `alarm_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '报警ID',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `stock_id` INT NOT NULL COMMENT '库存编号',
    `quantity` INT NOT NULL COMMENT '报警数量',
    `alarm_time` DATE NOT NULL COMMENT '报警时间',
    `reason` VARCHAR(255) NOT NULL COMMENT '报警原因',
    `handle_status` VARCHAR(10) NOT NULL COMMENT '处理状态（未处理、已处理）',
    `handle_time` DATE NOT NULL COMMENT '处理时间',
    `handle_person` VARCHAR(32) NOT NULL COMMENT '处理人',
    `handle_remark` VARCHAR(255) NOT NULL COMMENT '处理备注'
);


# 创建库存预警表
CREATE TABLE if not exists `STOCK_WARNING_TABLE` (
    `warning_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '预警ID',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `supplier_id` INT NOT NULL COMMENT '供应商编号',
    `quantity` INT NOT NULL COMMENT '预警数量',
    `warning_time` DATE NOT NULL COMMENT '预警时间',
    `reason` VARCHAR(255) NOT NULL COMMENT '预警原因',
    `handle_status` VARCHAR(10) NOT NULL COMMENT '处理状态（未处理、已处理）',
    `handle_time` DATE NOT NULL COMMENT '处理时间',
    `handle_person` VARCHAR(32) NOT NULL COMMENT '处理人',
    `handle_remark` VARCHAR(255) NOT NULL COMMENT '处理备注'
);

# 盘点表：盘点编号、盘点时间、仓库ID、盘点类型（周期盘点、全盘点）、员工ID、盘点备注
# 盘点单详情表：详情ID、盘点编号、商品编号、库存数量、实际库存数量、库存差异、差异原因
CREATE TABLE if not exists `inventory_check` (
    `check_id` INT NOT NULL COMMENT '盘点编号',
    `check_time` DATE NOT NULL COMMENT '盘点时间',
    `warehouse_id` INT NOT NULL COMMENT '仓库ID',
    `check_type` VARCHAR(10) NOT NULL COMMENT '盘点类型（周期盘点、全盘点）',
    `employee_id` INT NOT NULL COMMENT '员工ID',
    `check_remark` VARCHAR(255) NOT NULL COMMENT '盘点备注',
    PRIMARY KEY (`check_id`)
);

CREATE TABLE if not exists `inventory_check_detail` (
    `detail_id` INT NOT NULL COMMENT '详情ID',
    `check_id` INT NOT NULL COMMENT '盘点编号',
    `product_id` INT NOT NULL COMMENT '商品编号',
    `stock_quantity` INT NOT NULL COMMENT '库存数量',
    `actual_stock_quantity` INT NOT NULL COMMENT '实际库存数量',
    `stock_difference` INT NOT NULL COMMENT '库存差异',
    `difference_reason` VARCHAR(255) NOT NULL COMMENT '差异原因',
    PRIMARY KEY (`detail_id`),
    FOREIGN KEY (`check_id`) REFERENCES inventory_check(`check_id`)
);
ALTER TABLE `user` COMMENT '用户表';
ALTER TABLE `USER_SESSION_TABLE` COMMENT '会话表';
ALTER TABLE `USER_AUDIT_LOG_TABLE` COMMENT '审计日志表';
ALTER TABLE `ROLE_TABLE` COMMENT '角色表';
ALTER TABLE `USER_ROLE_MAPPING_TABLE` COMMENT '用户角色映射表';
ALTER TABLE `PERMISSION_TABLE` COMMENT '权限表';
ALTER TABLE `ROLE_PERMISSION_MAPPING_TABLE` COMMENT '角色权限映射表';
ALTER TABLE `DEPARTMENT_TABLE` COMMENT '部门表';
ALTER TABLE `DEPARTMENT_TABLE` COMMENT '部门表';
ALTER TABLE `PROJECT_TABLE` COMMENT '项目表';
ALTER TABLE `POSITION_TABLE` COMMENT '岗位表';
ALTER TABLE `SKILL_TABLE` COMMENT '技能表';
ALTER TABLE `EMPLOYEE_SKILL_MAPPING_TABLE` COMMENT '员工技能映射表';
ALTER TABLE `QUALIFICATION_TABLE` COMMENT '资格表';
ALTER TABLE `POSITION_QUALIFICATION_MAPPING_TABLE` COMMENT '岗位资格映射表';
ALTER TABLE `POSITION_CHANGE_TABLE` COMMENT '岗位变动表';
ALTER TABLE `CUSTOMER_TABLE` COMMENT '客户基本表';
ALTER TABLE `CUSTOMER_CREDIT_TABLE` COMMENT '客户信用表';
ALTER TABLE `CUSTOMER_CATEGORY_TABLE` COMMENT '客户分类表';
ALTER TABLE `CUSTOMER_TRADE_TABLE` COMMENT '客户交易表';
ALTER TABLE `SUPPLIER_TABLE` COMMENT '供应商基本表';
ALTER TABLE `SUPPLIER_CONTRACT_TABLE` COMMENT '供应商合同表';
ALTER TABLE `SUPPLIER_PERFORMANCE_TABLE` COMMENT '供应商绩效表';
ALTER TABLE `PRODUCT_TABLE` COMMENT '商品基本信息表';
ALTER TABLE `PRODUCT_PRICE_TABLE` COMMENT '商品价格信息表';
ALTER TABLE `SALE_ORDER_TABLE` COMMENT '销售订单表';
ALTER TABLE `SALE_ORDER_DETAIL_TABLE` COMMENT '销售订单详细表';
ALTER TABLE `INVOICE_TABLE` COMMENT '发票表';
ALTER TABLE `PAYMENT_TABLE` COMMENT '收款表';
ALTER TABLE `RETURN_ORDER_TABLE` COMMENT '退货单';
ALTER TABLE `WAREHOUSE_TABLE` COMMENT '仓库表';
ALTER TABLE `PURCHASE_ORDER_TABLE` COMMENT '采购订单表';
ALTER TABLE `PURCHASE_PAYMENT_TABLE` COMMENT '采购支付表';
ALTER TABLE `QUALITY_CHECK_TABLE` COMMENT '质量检测表';
ALTER TABLE `STOCK_TABLE` COMMENT '库存表';
ALTER TABLE `OUT_ORDER_TABLE` COMMENT '商品出库基本信息表';
ALTER TABLE `OUT_ORDER_DETAIL_TABLE` COMMENT '商品出库明细表';
ALTER TABLE `DAMAGED_TABLE` COMMENT '破损基本表';
ALTER TABLE `OVERFLOW_TABLE` COMMENT '溢出基本表';
ALTER TABLE `STOCK_ALARM_TABLE` COMMENT '库存报警表';
ALTER TABLE `STOCK_WARNING_TABLE` COMMENT '库存预警表';
ALTER TABLE `inventory_check` COMMENT '盘点表';
ALTER TABLE `inventory_check_detail` COMMENT '盘点单详情表';