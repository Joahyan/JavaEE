package com.briup.thread;

public class SynchronizedTest implements Runnable{

	Ticket ticket;
	
	public SynchronizedTest(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "SynchronizedTest [ticket=" + ticket + "]";
	}
	public SynchronizedTest() {

	}

	@Override
	public void run() {		
		while (ticket.getNum()>0) {
			synchronized (SynchronizedTest.class){//����	
				synchronized (this){//����		
			ticket.sale();
			System.out.println(Thread.currentThread().getName()+" ��Ʊ����Ʊ��"+ticket.getNum()+"��");			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
		}
		}
		
	}
	
	public static void main(String[] args) {
		Ticket ticket =new Ticket(100);
		SynchronizedTest tt =new SynchronizedTest(ticket);
		Thread t1 =new Thread(tt,"�Ϻ���վ");
		Thread t2 =new Thread(tt,"̫ԭ��վ");
		t1.start();
		t2.start();
	}

	
}
class Ticket{
	private int num;
	public void sale() {
		if (num>0) {
			num--;
		}
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Ticket(int num) {
		this.num = num;
	}

	public Ticket() {
	
	}
}