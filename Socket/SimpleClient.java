package com.briup.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
//客户端程序编写：
//①调用Socket()创建一个流套接字，并连接到服务器端； Socket(ip,port)
//②调用Socket类的getOutputStream()和getInputStream获取输出流和输入流，开始网络数据的发送和接收； 
//③最后关闭通信套接字。

public class SimpleClient {
	public static void main(String[] args) {
		try {
			Socket socket =new Socket("127.0.0.1",10000);
			while (true) {
				InputStream is =socket.getInputStream();
				DataInputStream dis =new DataInputStream(is);
				String str =dis.readUTF();
				System.out.println("服务器说:"+str);
				//键盘输入内容
				InputStream ips =System.in;
				//键盘输入的内容一般都是字符串，需一行一行读取  
				//需要用bufferReader readline()
				InputStreamReader isr =new InputStreamReader(ips);
				BufferedReader br =new BufferedReader(isr);
				//将键盘输入的内容读取到内存中，再将数据发送到服务器
				String msg =br.readLine();
				System.out.println("客户端说:"+msg);
				//构建输出流,发送数据
				OutputStream os =socket.getOutputStream();
				DataOutputStream dos =new DataOutputStream(os);
				dos.writeUTF(msg);
				dos.flush();
				
			}
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

}
