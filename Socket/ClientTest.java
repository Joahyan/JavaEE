package com.briup.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ClientTest {
	public static void main(String[] args) {
		Socket socket =null;
		OutputStream os =null;
		ObjectOutputStream oos =null;
		File file =new File("src/test.txt");
		FileInputStream fis;
		try {
			System.out.println("客户端开始读取文件");
			fis = new FileInputStream(file);
			InputStreamReader isr =new InputStreamReader(fis);
			BufferedReader br =new BufferedReader(isr);
			String str =null;
			String s[] =null;
			Student student =null;
			
			StudentCom sc =new StudentCom();
			Set<Student> set =new TreeSet<Student>(sc);
			
			while ((str=br.readLine())!=null) {				
				s=str.split(":");
				student =new Student(Integer.parseInt(s[0]),s[1],
									 Integer.parseInt(s[2]),s[3],
									 Integer.parseInt(s[4]));
				System.out.println(student);
				set.add(student);
			}
			System.out.println("客户端数据采集完成");
			System.out.println("客户端和服务器端正在建立连接...");
			socket =new Socket("127.0.0.1",10000);
			System.out.println("连接成功，准备发送...");
			os = socket.getOutputStream();
			oos =new ObjectOutputStream(os);
			oos.writeObject(set);
			oos.flush();
			System.out.println("客户端发送完成");
//			for (Student stu:list) {
//				System.out.println(stu);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (oos!=null)
						oos.close();
					if (os!=null) 
						os.close();
					if (socket!=null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
		
	}
}

class StudentCom implements Comparator,Serializable{

	/*
	 * 自定义排序(non-Javadoc)
	 * 需实现接口Comparator ，重写compare
	 * 返回1，交换顺序
	 * 返回-1，顺序不变
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Student s1 =(Student) o1;
		Student s2 =(Student) o2;
		if (s1.getScore()>s2.getScore()) {
			return 1;
		}else if (s1.getScore()<s2.getScore()) {
			return -1;
		}else {
			return 0;
		}
		
	}
	
}
