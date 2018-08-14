package com.briup.environment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;



//1.如何获得备份文件存放的目录信息
//2.如何把数据备份到文件
//3.如何读取文件中的备份数据
//4.如何实现备份数据时候的追加或者是覆盖
//5.如何控制读取备份数据后文件是否需要删除
public class BackUPImpl implements BackUP{

	private String filePath ;	
    public void init(Properties properties){
        filePath=properties.getProperty("backup-file");
    }
	@Override
	public void store(String fileName, Object obj, boolean append) {
//		System.out.println(fileName);
		File file =new File(filePath,fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				FileOutputStream fos =new FileOutputStream(file);
				ObjectOutputStream oos =new ObjectOutputStream(fos);
				oos.writeObject(obj);
				oos.flush();
				oos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public Object load(String fileName, boolean del){;
		File file =new File(filePath,fileName);
		Object o =null;
		if (!file.exists()) {
			return null;
		}
			
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois =new ObjectInputStream(fis);
				o =ois.readObject();
				ois.close();
				fis.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
							
		if (del == BackUP.LOAD_REMOVE) {
			file.delete();
		}
		return o;
				
	}
	
	public static void main(String[] args) {
		BackUPImpl back =new BackUPImpl();
		 
		try {
			  back.store("lina","my name is tom",BackUP.STORE_OVERRIDE);
	          back.store("lina","hello",BackUP.STORE_APPEND);
	          Object o;
	          o = back.load("lina",BackUP.LOAD_UNREMOVE);
	          System.out.println(o.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
