package com.briup.environment.client;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * Gather�ӿڹ涨�˲ɼ�ģ����Ӧ�õķ���
 * ��ʼ������������������Ŀ������Ϣ���вɼ�
 * ���ɼ������ݷ�װ��Collection<Environment>����
 */
public interface Gather extends EmdcModule{
	
	public Collection<Environment> gather()throws Exception;
		
	
}
