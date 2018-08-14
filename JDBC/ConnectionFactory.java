package com.briup.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
//��������������ֱ�Ӷ�ȡjdbc.properties������
public class ConnectionFactory {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static Properties properties;
	
	static{
		properties =new Properties();
		InputStream is;
		try {
			is = new FileInputStream(new File("src/jdbc.properties"));
			//load�������ȡ���ļ�
			properties.load(is);
			driver =properties.getProperty("driver");
			url =properties.getProperty("url");
			username =properties.getProperty("username");
			password =properties.getProperty("password");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection =DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return connection;	
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
