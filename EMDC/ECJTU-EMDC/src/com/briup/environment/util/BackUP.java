package com.briup.environment.util;

public interface BackUP extends EmdcModule{
	public void store(String fileName, Object obj,boolean append);
	
	Object load(String fileName,boolean del) throws Exception;
	
	/**
	 * 在保存数据时，在原来文件的基础上进行追加。
	 */
	public static final boolean STORE_APPEND=true;
	/**
	 * 在保存数据时，覆盖原来的文件
	 */
	public static final boolean STORE_OVERRIDE=false;
	/**
	 * 在读取数据同时，删除备份文件
	 */
	public static final boolean LOAD_REMOVE=true;
	/**
	 * 在读取数据同时，保留备份文件。
	 */
	public static final boolean LOAD_UNREMOVE=false;

	/**
	 * 通过键名获取已经备份的数据
	 * @param filePath
	 * @param flag
	 * @return
	 */

}
