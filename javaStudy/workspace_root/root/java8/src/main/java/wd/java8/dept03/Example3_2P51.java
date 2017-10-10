package wd.java8.dept03;

import java.util.function.IntConsumer;

import org.junit.Test;

public class Example3_2P51 {

	@Test
	public void main(){
		repeat(10,i->System.out.println("consumer:"+i));
		repeat1(10,()->System.out.println("hello word!"));
	}
	
	public void repeat(int n,IntConsumer action){
		for(int i =0;i<n;i++){
			action.accept(i);
		}
	}
	
	public void repeat1(int n,Runnable run){
		for (int i=0;i<n;i++){
			run.run();
		}
	}
}
