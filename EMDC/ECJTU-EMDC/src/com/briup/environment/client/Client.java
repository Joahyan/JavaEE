package com.briup.environment.client;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * Client�ӿ�������������������Ŀ����ģ��ͻ��˵Ĺ淶
 * Client�����þ��������������ͨ�ţ�������Ϣ
 */
public interface Client extends EmdcModule{
	public void send(Collection<Environment> coll)throws Exception;
}
