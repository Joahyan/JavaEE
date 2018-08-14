package com.briup.thread;

public class AccountTest {
	public static void main(String[] args) {
		Account account =new Account(1000);
		Boy boy =new Boy(account);
		Girl girl =new Girl(account);
		Thread t1 =new Thread(boy,"tom");
		Thread t2 =new Thread(girl,"girl");
		t1.start();
		t2.start();
	}
}
