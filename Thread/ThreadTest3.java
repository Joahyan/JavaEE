package com.briup.thread;

public class ThreadTest3 implements Runnable{
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()
						+"-interrupt:"+Thread.currentThread().isInterrupted());
//		for (int i = 0; i < 5; i++) {
//			System.out.println(Thread.currentThread().getName()+"-isAlive "+Thread.currentThread().isAlive()
//							+" -priority "+Thread.currentThread().getPriority()
//							+" -max_priority "+Thread.currentThread().MAX_PRIORITY
//							+" -min_priority "+Thread.currentThread().MIN_PRIORITY
//							+" -interropt "+Thread.currentThread().isInterrupted());
			try {
				Thread.currentThread().sleep(1000);
				System.out.println(Thread.currentThread().getName()+" ������������");
			} catch (InterruptedException e) { 
				System.out.println(Thread.currentThread().getName()+" �����쳣");
			}
			System.out.println(Thread.currentThread().getName()+" runִ�����");
		}	
//	}
	public static void main(String[] args) {
		ThreadTest3 tt =new ThreadTest3();
		Thread t1 =new Thread(tt,"first");
		Thread t2 =new Thread(tt,"second");
		//���ȼ�Խ��Խ������
//		t1.setPriority(10);
//		t2.setPriority(1);
		t1.start();
		if (!t1.isInterrupted()) {
			t1.interrupt();
		}
		t2.start();
		//û�����õĹ���
		//˭����˭����
//		try { 
//      //join()�ȴ����߳���ֹ
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

}
