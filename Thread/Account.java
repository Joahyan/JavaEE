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
	//消费
	public synchronized void withdraw(int money) {
		if(money>balance){
			System.out.println(Thread.currentThread().getName() +" 消费："+money+" 余额："+balance+"余额不足");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			balance -=money;
			System.out.println(Thread.currentThread().getName() +" 消费："+money+" 余额："+balance);
		}		
	}
	//存钱
	public synchronized void deposite(int money) {
		balance +=money;
		System.out.println(Thread.currentThread().getName() +" 存钱 ："+money+" 余额："+balance);
		notify();//通知
		
	}
	
	
}
