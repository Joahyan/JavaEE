package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionTest {
	private static Connection connection;
	public static void main(String[] args) {
		connection =ConnectionFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			getInsert();
			Savepoint a =connection.setSavepoint();
			getSelect();
			getUpdate();
			Savepoint b =connection.setSavepoint();
			getSelect();
			connection.rollback(a);
			getSelect();
//			connection.rollback(b);
//			getSelect();
//			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	
//		getDelete();
	
	}
	public static void getInsert() {
		String sql ="insert into student values(student_id.nextval,?,?)";
		try {
			PreparedStatement ps =connection.prepareStatement(sql);
			ps.setString(1, "briup");
			ps.setString(2, "000");
			int count =ps.executeUpdate();
			System.out.println("插入:"+count+"条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void getSelect() {
		String sql ="select * from student";
		try {
			Statement statement =connection.createStatement();
			ResultSet rs =statement.executeQuery(sql);
			while (rs.next()) {
				int id =rs.getInt(1);
				String name =rs.getString("name");
				String pwd =rs.getString(3);
				System.out.println("id:"+id+" name:"+name+" pwd:"+pwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void getUpdate() {
		String sql ="update student set pwd ='123' where name =?";
		try {
			PreparedStatement ps =connection.prepareStatement(sql);
			ps.setString(1, "briup");
			int count =ps.executeUpdate();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	public static void getDelete() {

		String sql ="delete from student";
		try {
			PreparedStatement ps =connection.prepareStatement(sql);
			int count =ps.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
