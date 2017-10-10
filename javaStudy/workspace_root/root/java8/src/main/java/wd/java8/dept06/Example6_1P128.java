package wd.java8.dept06;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class Example6_1P128 {

	public static AtomicLong nextNumber = new AtomicLong();
	
	@Test
	public void main(){
		long id = nextNumber.incrementAndGet();
	}
}
