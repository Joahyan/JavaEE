
package com.briup.environment.client;

import java.awt.geom.Path2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationAware;
import com.briup.environment.util.LoggerImpl;


public class GatherImpl implements Gather,ConfigurationAware{
	//Environment对象集合用来保持获取的对象数据
	Collection<Environment> coll =new ArrayList<Environment>();
	//采集原始文件
	private String path ="src/radwtmp";
	//保存上一次读取字节文件数
	private String path2 ="src/record";
	private static LoggerImpl log ;
//	private static BackUPImpl back;
	@Override
	public Collection<Environment> gather() throws Exception {
		/*
		 * 1、从path2指定的路径读取保存上一次得去到的字节数文件
		 * 第一次该文件不存在，需要分情况
		 * 2、读取radwtmp文件的有效字节数，将返回值保存到path2指定的文件中
		 * 3、先略过上一次读取的字节数，再进行本次解析
		 */
		File file =new File(path2);
		long num =0;
		if (file.exists()) {
			DataInputStream dis =new DataInputStream(new FileInputStream(file));
			num =dis.readLong();
		}
//		else {
//			file.createNewFile();
//		}
		/*
		 * RandomAccessFile流实现了略过功能
		 * 创建时提供两个参数
		 * 第一个参数，表示文件读取路径
		 * 第二个参数，表示以什么形式读取
		 * r代表只读
		 */
		RandomAccessFile raf=
				new RandomAccessFile(path, "r");
		long num2=raf.length();
		raf.seek(0);
		DataOutputStream dos =
				new DataOutputStream(new FileOutputStream(path2));
		dos.writeLong(num2);
		/*
		 * 1、构建缓存字符流按行读取数据
		 * 2、根据|分割字符串，根据第四个字段的不同分别赋予
		 * 温度，熟读，二氧化碳，关照强度的环境名称
		 * 3、第七个字段表示16进制环境值，将16进制转成10进制
		 * 如果温度和湿度的数据，前两个字节是温度数据，中间两个字节是湿度数据
		 * 如果是二氧化碳和关照强度，前两个字节就是对应的数据
		 * 4、温度和湿度是同一条记录，读取一行需要创建两个
		 * Environment对象，并且分别赋值
		 * 5、封装好的对象添加到coll集合中
		 */
		String s =null;
		String str[]=null;
		Environment environment =null;
		//统计温度和湿度条数(str[3]是16)
		int count =0;
		//统计关照强度条数(str[3]是256)
		int count2 =0;
		//统计二氧化碳条数(str[3]是1280)
		int count3 =0;
		
		while ((s=raf.readLine())!=null) {
			environment =new Environment();
			str =s.split("[|]");
			environment.setSrcId(str[0]);
			environment.setDesId(str[1]);
			environment.setDevId(str[2]);
			environment.setSersorAddress(str[3]);
			environment.setCount(Integer.parseInt(str[4]));
			environment.setCmd(str[5]);
			environment.setStatus(Integer.parseInt(str[7]));
			Long time =new Long(str[8]);
			Timestamp gather_date =new Timestamp(time);
			environment.setGather_date(gather_date);
			if ("16".equals(str[3])) {
				//根据温度转换公式将16进制转换成10进制
				float value =(float) 
						((Integer.parseInt(str[6].substring(0,4),16)*0.00268127)-46.85);
				environment.setName("温度");
				environment.setData(value);
				coll.add(environment);				
				Environment environment2 =new Environment();
				environment2 =environment;
				environment2.setSrcId(str[0]);
				environment2.setDesId(str[1]);
				environment2.setDevId(str[2]);
				environment2.setSersorAddress(str[3]);
				environment2.setCount(Integer.parseInt(str[4]));
				environment2.setCmd(str[5]);
				environment2.setStatus(Integer.parseInt(str[7]));
				environment2.setGather_date(gather_date);
				float value2 =(float) 
						((Integer.parseInt(str[6].substring(4,8),16)*0.00190735)-6);
				environment2.setName("湿度");
				environment2.setData(value2);
				coll.add(environment2);
				count++;
			}else {
				float value =Integer.parseInt(str[6].substring(0,4),16);
				if ("256".equals(str[3])) {
					environment.setName("光照强度");
					environment.setData(value);
					coll.add(environment);
					count2++;
				}
				if ("1280".equals(str[3])) {
					environment.setName("二氧化碳");
					environment.setData(value);
					coll.add(environment);
					count3++;
				}
			}
		}
		log.debug("采集环境数据:"+coll.size());
		log.info("温度，湿度:"+count);
		log.info("光照强度:"+count2);
		log.info("二氧化碳:"+count3);
		
//		System.out.println("采集环境数据:"+coll.size());
//		System.out.println("温度，湿度:"+count);
//		System.out.println("光照强度:"+count2);
//		System.out.println("二氧化碳:"+count3);
		
		return coll;
	}
	public static void main(String[] args) {
		try {
			new GatherImpl().gather();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init(Properties properties) throws Exception {
//		port=Integer.parseInt(properties.getProperty("port"));
		path =properties.getProperty("src-file");
		path2 =properties.getProperty("recored-file");
	}
	@Override
	public void setConfiguration(Configuration configuration) throws Exception {
		log=(LoggerImpl)configuration.getLogger();
//		back=(BackupImpl)configuration.getBackUP();
		
	}

}
