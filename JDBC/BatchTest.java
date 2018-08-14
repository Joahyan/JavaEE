package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
	public static void main(String[] args) {
		Connection connection =ConnectionFactory.getConnection();
		try {
			//适合处理不同的sql语句
			Statement statement =connection.createStatement();
			String sql1 ="insert into student values(2,'jack','234')";
			String sql2 ="update student set name ='rose' where id=1";
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.executeBatch();
			
				
//			PreparedStatement ps =connection.prepareStatement(sql);
			
			System.out.println("执行完成");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
