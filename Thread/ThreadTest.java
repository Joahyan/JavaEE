package com.briup.thread;

public class ThreadTest extends Thread{
	private static int ticket =100;
	
	@Override
	public void run() {
		while(ticket>0){
			ticket--;
			System.out.println(getName()+" ʣ�ࣺ"+ticket+"Ʊ");			
		}
	}
	
	public static void main(String[] args) {
		
		ThreadTest tt1=new ThreadTest();
		ThreadTest tt2=new ThreadTest();

		tt1.start();
		System.out.println(currentThread().getName()+"ִ�����");
		tt2.start();
		System.out.println(currentThread().getName()+"ִ�����");
//		tt1.run();
//		tt2.run();
		
	}
}
