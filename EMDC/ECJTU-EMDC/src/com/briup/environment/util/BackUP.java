package com.briup.environment.util;

public interface BackUP extends EmdcModule{
	public void store(String fileName, Object obj,boolean append);
	
	Object load(String fileName,boolean del) throws Exception;
	
	/**
	 * �ڱ�������ʱ����ԭ���ļ��Ļ����Ͻ���׷�ӡ�
	 */
	public static final boolean STORE_APPEND=true;
	/**
	 * �ڱ�������ʱ������ԭ�����ļ�
	 */
	public static final boolean STORE_OVERRIDE=false;
	/**
	 * �ڶ�ȡ����ͬʱ��ɾ�������ļ�
	 */
	public static final boolean LOAD_REMOVE=true;
	/**
	 * �ڶ�ȡ����ͬʱ�����������ļ���
	 */
	public static final boolean LOAD_UNREMOVE=false;

	/**
	 * ͨ��������ȡ�Ѿ����ݵ�����
	 * @param filePath
	 * @param flag
	 * @return
	 */

}
