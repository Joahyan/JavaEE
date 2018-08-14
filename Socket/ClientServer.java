package com.briup.net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class ClientServer {
	
	public static void main(String[] args) {
		PipedInputStream pis =new PipedInputStream();
		try {
			PipedOutputStream pos =new PipedOutputStream(pis);
			new Sender(pos, "发送方").start();
			new Fecher(pis, "接收方").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
//发送方
class Sender extends Thread{
	PipedOutputStream pos =null;
	public Sender(PipedOutputStream pos,String name) {
		this.pos =pos;
		setName(name);
	}
	@Override
	public void run() {
		try {
			FileReader fr =new FileReader("src/test2.txt");
			BufferedReader br =new BufferedReader(fr);
			String str =null;
			String s[]=null;
			Teacher teacher =null;
			Set<Teacher> set =new TreeSet<Teacher>();
			while ((str=br.readLine())!=null) {
				
				s=str.split(":");
				teacher =new Teacher(s[0],Integer.parseInt(s[1]),s[2]);
				System.out.println(teacher);
				set.add(teacher);				
			}
			ObjectOutputStream oos =new ObjectOutputStream(pos);
			oos.writeObject(set);
			oos.flush();
			System.out.println(getName()+"发送完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
}
class Fecher extends Thread{
	PipedInputStream pis =null;
	
	public Fecher(PipedInputStream pis,String name) {
		this.pis = pis;
		setName(name);
	}

	@Override
	public void run() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(pis);
			Set<Teacher> set =(Set<Teacher>) ois.readObject();
			System.out.println(getName()+"接收完毕,打印输出");
			for (Teacher t:set) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}



class Teacher implements Serializable{
	
	private String name;
	private int age;
	private String gender;
	
	public Teacher() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

	public Teacher(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	/*
	 * 自然排序(non-Javadoc)
	 * 当前对象比较传入对象升序
	 * 传入对象比较当前对象降序
	 */
//	@Override
//	public int compareTo(Object o) {
//		Teacher teacher =(Teacher) o;
//		return this.age -teacher.age;
//	}
	
}

