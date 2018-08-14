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
				System.out.println(Thread.currentThread().getName()+" 休眠正常结束");
			} catch (InterruptedException e) { 
				System.out.println(Thread.currentThread().getName()+" 休眠异常");
			}
			System.out.println(Thread.currentThread().getName()+" run执行完成");
		}	
//	}
	public static void main(String[] args) {
		ThreadTest3 tt =new ThreadTest3();
		Thread t1 =new Thread(tt,"first");
		Thread t2 =new Thread(tt,"second");
		//优先级越高越先运行
//		t1.setPriority(10);
//		t2.setPriority(1);
		t1.start();
		if (!t1.isInterrupted()) {
			t1.interrupt();
		}
		t2.start();
		//没被调用的挂起
		//谁调用谁运行
//		try { 
//      //join()等待该线程终止
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

}
