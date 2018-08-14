package com.briup.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
	public static void main(String[] args) {
		Connection connection =
				ConnectionFactory.getConnection();
		try {
			Statement statement =connection.createStatement();
			String name ="tom";
			String pwd ="123";
			//select * from student where name='tom' and pwd='tom';			
			String sql ="select * from student where name='"+name+"'and pwd ='"+pwd+"'";
			ResultSet rs =statement.executeQuery(sql);
			while (rs.next()) {
				int id =rs.getInt(1);
				String n =rs.getString(2);
				String p =rs.getString(3);
				System.out.println(id);
			}
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
