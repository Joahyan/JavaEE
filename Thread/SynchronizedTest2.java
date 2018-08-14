package com.briup.thread;

import java.awt.print.Printable;

public class SynchronizedTest2 implements Runnable{
	public static int num=10;
	@Override
	public void run() {
	print(num);	
	}
	
	public static void main(String[] args) {
		SynchronizedTest2 st =new SynchronizedTest2();
		Thread t1 =new Thread(st);
		Thread t2 =new Thread(st);
		t1.start();
		t2.start();
	}
	public synchronized void print(int num) {
		while (num>0) {
			num--;					
		System.out.println(Thread.currentThread().getName()+"-"+num);
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		
	}

}
