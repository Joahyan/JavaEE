package com.briup.environment.util;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;

import oracle.jdbc.util.Login;

/*
 * Configuration�ӿ��ṩ������ģ��Ĺ淶������ģ��ͨ��
 * Gather��Client��Server��DBStore��ģ���ʵ�������ʵ�����ݣ�
 * ͨ������ģ����Ի�ȡ����ģ���ʵ��
 */
public interface Configuration {
	//��ȡ��־ģ��ʵ��
	public Log getLogger()throws Exception;
	//��ȡ�������˵�ʵ��
	public Server getServer()throws Exception;
	//��ȡ�ͻ��˵�ʵ��
	public Client getClient()throws Exception;
	//��ȡ���ģ��ʵ��
	public DBStore getDbStore()throws Exception;
	//��ȡ�ɼ�ģ��ʵ��
	public Gather getGather()throws Exception;
	//��ȡ����ģ��ʵ��
	public BackUP getBackUP()throws Exception;
	//��ȡ��¼ģ��ʵ��
//	public Login getLogin()throws Exception;
}
