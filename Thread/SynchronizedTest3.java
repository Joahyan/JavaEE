package com.briup.thread;

public class SynchronizedTest3 implements Runnable{
	private MethodSync methodSync =new MethodSync();
	@Override
	public void run() {
		methodSync.method();
		
	}
	public static void main(String[] args) {
		SynchronizedTest3 st =new SynchronizedTest3();
		Thread t1 =new Thread(st);
		Thread t2 =new Thread(st);
		t1.start();
		t2.start();
	}
	
}
class MethodSync{
	private static int num =0;
	public synchronized void method() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() +" = "+num++);
		}
	}
}