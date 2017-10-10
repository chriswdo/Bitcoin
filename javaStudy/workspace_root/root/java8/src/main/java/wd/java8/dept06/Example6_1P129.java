package wd.java8.dept06;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Example6_1P129 {

	public static AtomicLong largest = new AtomicLong();
	
	public void main(){
		largest.updateAndGet(x -> Math.max(x, 10));
		LongAdder longadd = new LongAdder();
		longadd.add(10);
	}
}
