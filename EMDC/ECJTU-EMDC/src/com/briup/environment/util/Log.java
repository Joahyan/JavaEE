package com.briup.environment.util;
/*
 * �ýӿ��ṩ����־ģ��Ĺ淶����־ģ�齫��־��Ϣ����Ϊ���ּ���
 * ��ͬ�������־�ļ�¼��ʽ����¼��ʽ������ͬ
 */
public interface Log extends EmdcModule{
	//��¼debug������־
	public void debug(String msg);
	//��¼info������־
	public void info(String msg);
	//��¼warm������־
	public void warm(String msg);
	//��¼error������־
	public void error(String msg);
	//��¼fatal������־
	public void fatal(String msg);
}
