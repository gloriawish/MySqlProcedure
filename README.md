# MySqlProcedure
使用druid_procedure模拟执行存储过程的例子

# 使用方法
安装mysql5.6数据库, 然后创建一个数据库 sp(使用 create database sp;)<br>
使用create table __store_procedure(id int primary key not null auto_increment,procedureName varchar(100),parameterNumber int,source text);<br>
创建一个表<br>
然后执行test.java

## 注意事项
需要引入druid_procedure项目
https://github.com/zhujunxxxxx/druid_procedure

##例子

		public static void main(String[] args) {
		
				String create = "create procedure p1() "
						+ " begin "
						+ " declare @x int; "
						+ " set @x=10;"
						+ " while @x > 0 do "
						+ " set @x=@x-1;"
						+ " end while;"
						+ " end";
		
				if(MySqlProcedure.createProcedure(create)) {
					MySqlProcedure.callProcedure("call p1()");
				}
		
				MySqlProcedure.dropProcedure("drop procedure IF EXISTS  p1");
			}