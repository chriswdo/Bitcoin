package wd.java8.dept06;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class Example6_2P135 {

	@Test
	public void main(){
		ConcurrentHashMap<String,Integer>map = new ConcurrentHashMap<String,Integer>();
		map.search(Long.MAX_VALUE, (k,v)->v<1000?v:null);
//		map.forEach(parallelismThreshold, action);
	}
}
