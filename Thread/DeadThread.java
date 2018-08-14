package com.briup.thread;

public class DeadThread implements Runnable{
	public int flag;
	public static Object o1 =new Object();//����
	public static Object o2 =new Object();//��
	@Override
	public void run() {
		if (flag == 0) {
			synchronized (o1) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("1���ܳԷ���");
				}
			}
		}else if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("2���ܳԷ���");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		DeadThread dt1 =new DeadThread();
		DeadThread dt2 =new DeadThread();
		dt1.flag=0;
		dt2.flag=1;
		Thread t1 =new Thread(dt1);
		Thread t2 =new Thread(dt2);
		t1.start();
		t2.start();
	}

}
