package com.ecnu.mysql;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLCallStatement;
import com.alibaba.druid.sql.ast.statement.SQLDropProcedureStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlCreateProcedureStatement;
import com.alibaba.druid.sql.dialect.mysql.executor.MySqlProcedureExecutor;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class MySqlProcedure {
	
	private static String IP = "127.0.0.1";
	private static int PORT = 3306;
	private static String DB = "sp";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	static
	{
		MySqlProcedureExecutor.USER = USERNAME;
		MySqlProcedureExecutor.PASS = PASSWORD;
		MySqlProcedureExecutor.URL = String.format("jdbc:mysql://%s:%d/%s", IP, PORT, DB);
	}
	/**
	 * 创建存储过程
	 * @param sql
	 * @return
	 */
	public static boolean createProcedure(String sql) {
		
		MySqlStatementParser parser=new MySqlStatementParser(sql);
		List<SQLStatement> statementList = null;
		try {
			statementList = parser.parseStatementList();
		} catch (Exception e) {
			return false;
		}
    	if(statementList == null || statementList.size() <= 0) {
    		return false;
    	}
    	SQLStatement stmt = statementList.get(0);
		if(!(stmt instanceof MySqlCreateProcedureStatement)) {
			return false;
		}
		MySqlCreateProcedureStatement sp=(MySqlCreateProcedureStatement)stmt;
		if (MySqlProcedureExecutor.executeCreateProcedure(sp, sql) != MySqlProcedureExecutor.SUCCESS) {
			return false;
		}
		return true;
	}
	
	/**
	 * 删除存储过程
	 * @param sql
	 * @return
	 */
	public static boolean dropProcedure(String sql) {
		MySqlStatementParser parser=new MySqlStatementParser(sql);
		List<SQLStatement> statementList = null;
		try {
			statementList = parser.parseStatementList();
		} catch (Exception e) {
			return false;
		}
    	if(statementList == null || statementList.size() <= 0) {
    		return false;
    	}
    	SQLStatement stmt = statementList.get(0);
		if(!(stmt instanceof SQLDropProcedureStatement)) {
			return false;
		}
		SQLDropProcedureStatement drop=(SQLDropProcedureStatement)stmt;
		if (MySqlProcedureExecutor.executeDropProcedure(drop) != MySqlProcedureExecutor.SUCCESS) {
			return false;
		}
		return true;
	}
	
	/**
	 * 调用存储过程
	 * @param sql
	 * @return
	 */
	public static boolean callProcedure(String sql) {
		MySqlStatementParser parser=new MySqlStatementParser(sql);
		List<SQLStatement> statementList = null;
		try {
			statementList = parser.parseStatementList();
		} catch (Exception e) {
			return false;
		}
    	if(statementList == null || statementList.size() <= 0) {
    		return false;
    	}
    	SQLStatement stmt = statementList.get(0);
		if(!(stmt instanceof SQLCallStatement)) {
			return false;
		}
		SQLCallStatement call=(SQLCallStatement)stmt;
		if (MySqlProcedureExecutor.executeCallProcedure(call) != MySqlProcedureExecutor.SUCCESS) {
			return false;
		}
		return true;
	}

}
