
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
	//Environment���󼯺��������ֻ�ȡ�Ķ�������
	Collection<Environment> coll =new ArrayList<Environment>();
	//�ɼ�ԭʼ�ļ�
	private String path ="src/radwtmp";
	//������һ�ζ�ȡ�ֽ��ļ���
	private String path2 ="src/record";
	private static LoggerImpl log ;
//	private static BackUPImpl back;
	@Override
	public Collection<Environment> gather() throws Exception {
		/*
		 * 1����path2ָ����·����ȡ������һ�ε�ȥ�����ֽ����ļ�
		 * ��һ�θ��ļ������ڣ���Ҫ�����
		 * 2����ȡradwtmp�ļ�����Ч�ֽ�����������ֵ���浽path2ָ�����ļ���
		 * 3�����Թ���һ�ζ�ȡ���ֽ������ٽ��б��ν���
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
		 * RandomAccessFile��ʵ�����Թ�����
		 * ����ʱ�ṩ��������
		 * ��һ����������ʾ�ļ���ȡ·��
		 * �ڶ�����������ʾ��ʲô��ʽ��ȡ
		 * r����ֻ��
		 */
		RandomAccessFile raf=
				new RandomAccessFile(path, "r");
		long num2=raf.length();
		raf.seek(0);
		DataOutputStream dos =
				new DataOutputStream(new FileOutputStream(path2));
		dos.writeLong(num2);
		/*
		 * 1�����������ַ������ж�ȡ����
		 * 2������|�ָ��ַ��������ݵ��ĸ��ֶεĲ�ͬ�ֱ���
		 * �¶ȣ������������̼������ǿ�ȵĻ�������
		 * 3�����߸��ֶα�ʾ16���ƻ���ֵ����16����ת��10����
		 * ����¶Ⱥ�ʪ�ȵ����ݣ�ǰ�����ֽ����¶����ݣ��м������ֽ���ʪ������
		 * ����Ƕ�����̼�͹���ǿ�ȣ�ǰ�����ֽھ��Ƕ�Ӧ������
		 * 4���¶Ⱥ�ʪ����ͬһ����¼����ȡһ����Ҫ��������
		 * Environment���󣬲��ҷֱ�ֵ
		 * 5����װ�õĶ�����ӵ�coll������
		 */
		String s =null;
		String str[]=null;
		Environment environment =null;
		//ͳ���¶Ⱥ�ʪ������(str[3]��16)
		int count =0;
		//ͳ�ƹ���ǿ������(str[3]��256)
		int count2 =0;
		//ͳ�ƶ�����̼����(str[3]��1280)
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
				//�����¶�ת����ʽ��16����ת����10����
				float value =(float) 
						((Integer.parseInt(str[6].substring(0,4),16)*0.00268127)-46.85);
				environment.setName("�¶�");
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
				environment2.setName("ʪ��");
				environment2.setData(value2);
				coll.add(environment2);
				count++;
			}else {
				float value =Integer.parseInt(str[6].substring(0,4),16);
				if ("256".equals(str[3])) {
					environment.setName("����ǿ��");
					environment.setData(value);
					coll.add(environment);
					count2++;
				}
				if ("1280".equals(str[3])) {
					environment.setName("������̼");
					environment.setData(value);
					coll.add(environment);
					count3++;
				}
			}
		}
		log.debug("�ɼ���������:"+coll.size());
		log.info("�¶ȣ�ʪ��:"+count);
		log.info("����ǿ��:"+count2);
		log.info("������̼:"+count3);
		
//		System.out.println("�ɼ���������:"+coll.size());
//		System.out.println("�¶ȣ�ʪ��:"+count);
//		System.out.println("����ǿ��:"+count2);
//		System.out.println("������̼:"+count3);
		
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
