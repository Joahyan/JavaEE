package com.briup.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;


public class ServerTest {
	private ServerSocket ss;
	private Socket s;
	private InputStream is;
	private ObjectInputStream ois;
	
	public void shutdown() {
			try {
				if (ois!=null) ois.close();
				if (is!=null) is.close();
				if (s!=null) s.close();
				if (ss!=null) ss.close();
				System.out.println("资源已经释放");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	class Handler extends Thread{

		@Override
		public void run() {
			System.out.println("服务器已经开启,等待客户端连接");
			try {
				ss =new ServerSocket(10000);
				s =ss.accept();
				System.out.println("客户端和服务器连接成功,"+"服务器准备接受数据...");
				is =s.getInputStream();
				ois =new ObjectInputStream(is);
				Set<Student> set = (Set<Student>) ois.readObject();
				System.out.println("数据接受完成,准备打印输出");
				for (Student stu:set) {
					System.out.println(stu);
				}
				shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		ServerTest st=new ServerTest();
//		ServerTest t2=new ServerTest();
		Handler handler = st.new Handler();
		handler.start();

	}
}
