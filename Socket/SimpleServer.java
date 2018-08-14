package com.briup.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.PortableServer.Servant;
//服务器程序编写：
//①调用ServerSocket(int port)创建一个服务器端套接字，并绑定到指定端口上；
//②调用accept()，监听连接请求，如果客户端请求连接，则接受连接，返回通信套接字；
//③调用Socket类的getOutputStream()和getInputStream获取输出流和输入流，开始网络数据的发送和接收；
//④最后关闭通信套接字。
public class SimpleServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss =new ServerSocket(10000);
			Socket s =ss.accept();
			while (true) {
				InputStream is =System.in;
				BufferedReader br =new BufferedReader(new InputStreamReader(is));
				String str =br.readLine();
				System.out.println("服务器说:"+str);
				//服务器将获取到键盘输入的内容发送给客户端
				OutputStream os =s.getOutputStream();
				DataOutputStream dos =new DataOutputStream(os);
				dos.writeUTF(str);
				dos.flush();
				//服务器接受客服端发来的数据
				InputStream ips =s.getInputStream();
				DataInputStream dis =new DataInputStream(ips);
				String msg =dis.readUTF();
				System.out.println("客服端说:"+msg);
				}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
