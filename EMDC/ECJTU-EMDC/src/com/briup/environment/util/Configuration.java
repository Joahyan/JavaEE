package com.briup.environment.util;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;

import oracle.jdbc.util.Login;

/*
 * Configuration接口提供了配置模块的规范，配置模块通过
 * Gather，Client，Server，DBStore等模块的实现类进行实例传递，
 * 通过配置模块可以获取各个模块的实例
 */
public interface Configuration {
	//获取日志模块实例
	public Log getLogger()throws Exception;
	//获取服务器端的实例
	public Server getServer()throws Exception;
	//获取客户端的实例
	public Client getClient()throws Exception;
	//获取入库模块实例
	public DBStore getDbStore()throws Exception;
	//获取采集模块实例
	public Gather getGather()throws Exception;
	//获取备份模块实例
	public BackUP getBackUP()throws Exception;
	//获取登录模块实例
//	public Login getLogin()throws Exception;
}
