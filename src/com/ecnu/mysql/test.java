package com.ecnu.mysql;

public class test {

	public static void main(String[] args) {
		
		String create = "create procedure p1() "
				+ " begin "
				+ " declare @x int; "
				+ " set @x=100000;"
				+ " while @x > 0 do "
				+ " set @x=@x-1;"
				+ " end while;"
				+ " end";
		
		if(MySqlProcedure.createProcedure(create)) {
			MySqlProcedure.callProcedure("call p1()");
		}
		
		MySqlProcedure.dropProcedure("drop procedure IF EXISTS  p1");
	}
	
}
