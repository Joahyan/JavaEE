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
//�����������д��
//�ٵ���ServerSocket(int port)����һ�����������׽��֣����󶨵�ָ���˿��ϣ�
//�ڵ���accept()������������������ͻ����������ӣ���������ӣ�����ͨ���׽��֣�
//�۵���Socket���getOutputStream()��getInputStream��ȡ�����������������ʼ�������ݵķ��ͺͽ��գ�
//�����ر�ͨ���׽��֡�
public class SimpleServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss =new ServerSocket(10000);
			Socket s =ss.accept();
			while (true) {
				InputStream is =System.in;
				BufferedReader br =new BufferedReader(new InputStreamReader(is));
				String str =br.readLine();
				System.out.println("������˵:"+str);
				//����������ȡ��������������ݷ��͸��ͻ���
				OutputStream os =s.getOutputStream();
				DataOutputStream dos =new DataOutputStream(os);
				dos.writeUTF(str);
				dos.flush();
				//���������ܿͷ��˷���������
				InputStream ips =s.getInputStream();
				DataInputStream dis =new DataInputStream(ips);
				String msg =dis.readUTF();
				System.out.println("�ͷ���˵:"+msg);
				}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
