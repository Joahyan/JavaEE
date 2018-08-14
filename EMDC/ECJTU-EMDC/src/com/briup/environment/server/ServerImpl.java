package com.briup.environment.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUP;
import com.briup.environment.util.BackUPImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.Log;
import com.briup.environment.util.LoggerImpl;
import com.briup.net.Student;


public class ServerImpl implements Server,ConfigurationAware{
	private int port =10000;
	private ServerSocket ss;
	private Socket s;
	private InputStream is;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private static LoggerImpl log;
	private static BackUPImpl back;
	public static void main(String[] args) {
		
		try {
			new ServerImpl().reciver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override	
	public void reciver() throws Exception {
		Handler handler =new ServerImpl().new Handler();
		handler.start();

	}

	@Override
	public void shutdown() {
		try {
			if (ois!=null) ois.close();
			if (bis!=null) bis.close();
			if (is!=null) is.close();
			if (s!=null) s.close();
			if (ss!=null) ss.close();
			log.info("资源已经释放");
//			System.out.println("资源已经释放");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	class Handler extends Thread{
//		private Collection<Environment> coll;
//	
//
//		public Collection<Environment> getColl() {
//			return coll;
//		}
//
//
//		public void setColl(Collection<Environment> coll) {
//			this.coll = coll;
//		}

		@Override
		public void run() {
			log.info("服务器已经开启,等待客户端连接");
//			System.out.println("服务器已经开启,等待客户端连接");
			try {
				ss =new ServerSocket(port);
				s =ss.accept();
				log.info("客户端和服务器连接成功,"+"服务器准备接受数据...");
//				System.out.println("客户端和服务器连接成功,"+"服务器准备接受数据...");
				is =s.getInputStream();
				ois =new ObjectInputStream(is);
				Collection<Environment> coll = (Collection<Environment>)back.load("serverBack",BackUP.LOAD_REMOVE);
				log.info("数据接受完成,准备打印输出");
//				System.out.println("数据接受完成,准备打印输出");		
				log.info("数据库接收完成，一共接收："+coll.size());
//				System.out.println("数据库接收完成，一共接收："+coll.size());
				DBStore store =new DBStoreImpl();
				store.SaveDB(coll);
				shutdown();
				log.info("网络连接资源关闭，开启数据库，准备入库");
//				System.out.println("网络连接资源关闭，开启数据库，准备入库");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void init(Properties properties) throws Exception {
		port =Integer.parseInt(properties.getProperty("port"));
		
	}
	@Override
	public void setConfiguration(Configuration configuration) throws Exception {
		log=(LoggerImpl)configuration.getLogger();
		back =(BackUPImpl)configuration.getBackUP();
	}
}
