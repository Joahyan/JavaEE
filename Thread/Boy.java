package com.briup.thread;

public class Boy implements Runnable{
	private Account account;
	
	public Boy() {
	}

	public Boy(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
			while (true) {
				int am = (int)(Math.random()*1000);
				account.deposite(am);			
				try {
					Thread.currentThread().sleep(am*5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}	
	}
	
}
