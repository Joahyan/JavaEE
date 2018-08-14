package com.briup.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class ExecutorTest {
	public static void main(String[] args) {
			//用线程池创建多线程
		//用线程池创建多线程
		ExecutorService pool=Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					
					
				}
			});
		}
		
		Executors.newCachedThreadPool().execute(new Runnable() {
			
			@Override
			public void run() {
		
				
			}
		});
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		});
	}
	



}
