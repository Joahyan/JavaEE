package com.briup.thread;

public class Account implements Runnable{
	private int balance;

	@Override
	public void run() {
		
	}
	public Account() {
	}

	public Account(int balance) {
		this.balance = balance;
	}
	//����
	public synchronized void withdraw(int money) {
		if(money>balance){
			System.out.println(Thread.currentThread().getName() +" ���ѣ�"+money+" ��"+balance+"����");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			balance -=money;
			System.out.println(Thread.currentThread().getName() +" ���ѣ�"+money+" ��"+balance);
		}		
	}
	//��Ǯ
	public synchronized void deposite(int money) {
		balance +=money;
		System.out.println(Thread.currentThread().getName() +" ��Ǯ ��"+money+" ��"+balance);
		notify();//֪ͨ
		
	}
	
	
}
