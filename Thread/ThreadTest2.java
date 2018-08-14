package com.briup.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest2 implements Runnable{
	private int ticket =100;//实现方法时可用非静态，因为主方法中只需new一个线程
	@Override
	public void run() {
		while (ticket>0) {
			ticket--;
			System.out.println(Thread.currentThread().getName() +" 剩余："+ticket+"票");
			
		}
		
	}
	public static void main(String[] args) {
		ThreadTest2 tt =new ThreadTest2();
//		ThreadTest2 tt2 =new ThreadTest2();
//		tt.start();//这种写法不能直接调用start()方法
		Thread t1 =new Thread(tt);
		Thread t2 =new Thread(tt);
		t1.start();
		t2.start();
			
	}

}
