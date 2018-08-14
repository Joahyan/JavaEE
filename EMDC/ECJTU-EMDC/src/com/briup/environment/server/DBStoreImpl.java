package com.briup.environment.server;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.client.Gather;
import com.briup.environment.client.GatherImpl;
import com.briup.environment.util.BackUPImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.jdbc.ConnectionFactory;

public class DBStoreImpl implements DBStore,ConfigurationAware{
	 private int batchSize =500;
	 private String driver ="oracle.jdbc.driver.OracleDriver";
	 private String url ="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	 private String userName ="envir";
	 private String passWord ="envir";
	 private Connection connection;
	 private BackUPImpl back;
	@Override
	public void SaveDB(Collection<Environment> db) throws Exception {
		
		Class.forName(driver);
        connection = DriverManager.getConnection(url,userName,passWord);	
		PreparedStatement ps=null;
		Calendar calendar =null;
		int i=0;
		for (Environment c:db) {
			Timestamp date=c.getGather_date();			
			calendar =Calendar.getInstance();
			calendar.setTime(date);			
			int day =0;
			if (ps!=null||day!=calendar.get(Calendar.DAY_OF_MONTH)) {
				day =calendar.get(Calendar.DAY_OF_MONTH);
				if (ps!=null) {							
					ps.executeBatch();	
					ps.clearBatch();
					ps.close();
				}
				String sql ="insert into e_detail_"+day+" values(?,?,?,?,?,?,?,?,?)";
				ps =connection.prepareStatement(sql);
			}	
			ps.setString(1, c.getName());
			ps.setString(2, c.getSrcId());
			ps.setString(3, c.getDevId());
			ps.setString(4, c.getSersorAddress());
			ps.setInt(5, c.getCount());
			ps.setString(6, c.getCmd());
			ps.setInt(7, c.getStatus());					
			ps.setTimestamp(9,date);
			ps.setFloat(8, c.getData());
			System.out.println(c);
			ps.addBatch();
			i++;				
			if (i%batchSize==0) {
				ps.executeBatch();
				ps.clearBatch();				
			}
							
		}
	
	}

	@Override
	public void init(Properties properties) throws Exception {
		url=properties.getProperty("url");
		driver=properties.getProperty("driver");
		userName=properties.getProperty("userName");
		passWord=properties.getProperty("passWord");
	
		
	}

	@Override
	public void setConfiguration(Configuration configuration) throws Exception {
		back =(BackUPImpl)configuration.getBackUP();
		
		
	}


}
