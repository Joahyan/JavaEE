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
//�ͻ��˳����д��
//�ٵ���Socket()����һ�����׽��֣������ӵ��������ˣ� Socket(ip,port)
//�ڵ���Socket���getOutputStream()��getInputStream��ȡ�����������������ʼ�������ݵķ��ͺͽ��գ� 
//�����ر�ͨ���׽��֡�

public class SimpleClient {
	public static void main(String[] args) {
		try {
			Socket socket =new Socket("127.0.0.1",10000);
			while (true) {
				InputStream is =socket.getInputStream();
				DataInputStream dis =new DataInputStream(is);
				String str =dis.readUTF();
				System.out.println("������˵:"+str);
				//������������
				InputStream ips =System.in;
				//�������������һ�㶼���ַ�������һ��һ�ж�ȡ  
				//��Ҫ��bufferReader readline()
				InputStreamReader isr =new InputStreamReader(ips);
				BufferedReader br =new BufferedReader(isr);
				//��������������ݶ�ȡ���ڴ��У��ٽ����ݷ��͵�������
				String msg =br.readLine();
				System.out.println("�ͻ���˵:"+msg);
				//���������,��������
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
