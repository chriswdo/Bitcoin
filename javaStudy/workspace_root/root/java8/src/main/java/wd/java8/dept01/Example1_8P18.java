package wd.java8.dept01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class Example1_8P18 {

	@Test
	public void main(){
		Comparator comparator = Comparator.comparing(String::length);
		List<String> list = new ArrayList<String>();
		list.add("aaaa");
		list.add("bb");
		list.add("dfafafa");
		list.sort(comparator);
		for(String str : list){
			System.out.println(str);
		}
	}
}
