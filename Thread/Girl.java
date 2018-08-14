package com.briup.thread;

public class Girl implements Runnable{
	private Account account;
 
	public Girl(Account account) {
	this.account = account;
}	
	public Girl() {	
	}
	@Override
	public void run() {
		while (true) {
			int am =(int)(Math.random()*1000);
			account.withdraw(am);
			try {
				Thread.currentThread().sleep(am);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
