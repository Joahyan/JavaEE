

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
		log.info("����Ŭ�����������������...");
//		System.out.println("����Ŭ�����������������...");
		Socket socket=new Socket(ip,port);
		log.info("�ͻ�������������ӳɹ������ڷ�����Ϣ");
//		System.out.println("�ͻ�������������ӳɹ������ڷ�����Ϣ");
		
		/*
		 * �ͻ��˷������ݣ�ͨ��socket���������
		 * ���ڷ��Ͳɼ�ģ���ռ��õ�environment���϶���
		 * ���з�װ��Object�����
		 */
		OutputStream os =socket.getOutputStream();
		BufferedOutputStream bos =new BufferedOutputStream(os);
		ObjectOutputStream oos =new ObjectOutputStream(bos);
		oos.writeObject(coll);
		log.info("�ͻ��˷����������");
//		System.out.println("�ͻ��˷����������");
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
