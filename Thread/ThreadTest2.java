package com.briup.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest2 implements Runnable{
	private int ticket =100;//ʵ�ַ���ʱ���÷Ǿ�̬����Ϊ��������ֻ��newһ���߳�
	@Override
	public void run() {
		while (ticket>0) {
			ticket--;
			System.out.println(Thread.currentThread().getName() +" ʣ�ࣺ"+ticket+"Ʊ");
			
		}
		
	}
	public static void main(String[] args) {
		ThreadTest2 tt =new ThreadTest2();
//		ThreadTest2 tt2 =new ThreadTest2();
//		tt.start();//����д������ֱ�ӵ���start()����
		Thread t1 =new Thread(tt);
		Thread t2 =new Thread(tt);
		t1.start();
		t2.start();
			
	}

}
