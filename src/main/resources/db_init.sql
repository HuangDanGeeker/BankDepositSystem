use bank_deposit_system;

-- 建表
-- 1.创建员工表
drop table staff;
create TABLE staff (name VARCHAR(20), No VARCHAR(20) PRIMARY KEY, passwd INT DEFAULT 111111, birthday INT, phone VARCHAR(20)) CHARSET=utf8;
-- 2.创建客户表
drop table customer;
create TABLE customer (name VARCHAR(20), no VARCHAR(20) PRIMARY KEY, passwd INT DEFAULT 111111,birthday INT, phone VARCHAR(20), address VARCHAR(40)) CHARSET=utf8;

-- 数据操作
-- 1.插入员工
INSERT INTO staff VALUES ('HuangDan', 0902150228, 111111, 970101, 18373151462);

-- 2.插入客户
INSERT INTO customer VALUES ('HuangDan', 0902150228, 111111, 970101, 18373151462, '中南大学-升华公寓');
