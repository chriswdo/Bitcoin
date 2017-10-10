package wd.java8.dept06;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class Example6_2P133 {

	@Test
	public void main(){
		ConcurrentHashMap <String,Integer>map = new ConcurrentHashMap<String,Integer>();
		String key="key";
		map.compute(key, (k,v)->v==null?0: (v+1));
		System.out.println(map.get(key));
		map.merge("aaa", 1, (oldValue,newValue)->{
			System.out.println(oldValue);
			System.out.println(newValue);
			return oldValue+newValue;});
		System.out.println(map.get(key));
		System.out.println(map.get("aaa"));
	}
}
