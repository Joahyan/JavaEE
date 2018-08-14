package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {
	public static void main(String[] args) {
		Connection connection =ConnectionFactory.getConnection();
		String sql ="select * from student where name=?and pwd =?";
		try {
			PreparedStatement ps =connection.prepareStatement(sql);
			ps.setString(1, "tom");
			ps.setString(2, "tom");
			ResultSet rs =ps.executeQuery();
			while (rs.next()) {
				System.out.println("id:"+rs.getInt(1));
				System.out.println("id:"+rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
