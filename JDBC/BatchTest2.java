package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchTest2 {
	public static void main(String[] args) {
		Connection connection =ConnectionFactory.getConnection();
				//适用在sql语句相同，参数不同
				String sql ="insert into student values(?,?,?)";
				try {
					PreparedStatement ps =connection.prepareStatement(sql);
					for (int i = 0; i < 502; i++) {
						ps.setInt(1, i);
						ps.setString(2,"briup"+ i);
						ps.setString(3, "000");
						ps.addBatch();
						System.out.println("插入："+i);
						if (i%100==0) {
							ps.executeBatch();
							ps.clearBatch();
						}
					}
					ps.executeBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
}
