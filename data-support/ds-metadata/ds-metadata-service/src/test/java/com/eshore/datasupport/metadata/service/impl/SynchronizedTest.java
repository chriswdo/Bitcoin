package com.eshore.datasupport.metadata.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class SynchronizedTest {

	@Test
	public void main(){
		
		Thread thread1 = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=10;i>0;i--){
					Integer ii=Integer.valueOf(i);
					foo(ii);
				}
			}
		});
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					Integer ii=Integer.valueOf(i);
					foo(ii);
				}
			}
		});
		thread2.start();
		while(true){
			
		}
	}
	
	public int count = 0;
	public   void foo(Integer param){
		synchronized(param){
			System.out.println("param"+param);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println(count);
		}
	}

}
