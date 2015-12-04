# MySqlProcedure
使用druid_procedure模拟执行存储过程的例子

# 使用方法
安装mysql5.6数据库, 然后创建一个数据库 sp(使用 create database sp;)
使用create table __store_procedure(id int primary key not null auto_increment,procedureName varchar(100),parameterNumber int,source text);
创建一个表
然后执行test

## 注意事项
需要引入druid_procedure项目
https://github.com/zhujunxxxxx/druid_procedure