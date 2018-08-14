

package com.briup.environment.client;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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



public class ClientImpl implements Client,ConfigurationAware{
	private String ip="127.0.0.1";
	private int port =10000;
	private  LoggerImpl log ;
	private static  BackUPImpl back;
	@Override
	public void send(Collection<Environment> coll) throws Exception {
		log.info("正在努力与服务器建立连接...");
//		System.out.println("正在努力与服务器建立连接...");
		Socket socket=new Socket(ip,port);
		log.info("客户端与服务器连接成功，正在发送信息");
//		System.out.println("客户端与服务器连接成功，正在发送信息");
		
		/*
		 * 客户端发送数据，通过socket创建输出流
		 * 由于发送采集模块收集好的environment集合对象
		 * 所有封装成Object输出流
		 */
		OutputStream os =socket.getOutputStream();
		BufferedOutputStream bos =new BufferedOutputStream(os);
		ObjectOutputStream oos =new ObjectOutputStream(bos);
		oos.writeObject(coll);
		log.info("客户端发送数据完成");
//		System.out.println("客户端发送数据完成");
		oos.flush();
		if(oos!=null) oos.close();
		if(bos!=null) bos.close();
		if(os!=null) os.close();
		if(socket!=null) socket.close();
	}
	public static void main(String[] args) {
		Gather gather =new GatherImpl();
		try {
			Collection<Environment> coll = gather.gather();
			Client client =new ClientImpl();
			client.send(coll);
			back.store("clientBack",coll,BackUP.STORE_OVERRIDE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init(Properties properties) throws Exception {
		ip=properties.getProperty("ip");
		port=Integer.parseInt(properties.getProperty("port"));
	}
	@Override
	public void setConfiguration(Configuration configuration) throws Exception {
		log=(LoggerImpl)configuration.getLogger();
		back=(BackUPImpl)configuration.getBackUP();
		
	}
}
