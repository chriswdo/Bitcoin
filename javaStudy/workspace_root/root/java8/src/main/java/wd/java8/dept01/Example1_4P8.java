package wd.java8.dept01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class Example1_4P8 {

	@Test
	public void main(){
		List<String>list = new ArrayList<String>();
		list.add("afahfiah");
		list.add("fdfdfd");
		list.add("DFAA");
		list.sort(String::compareToIgnoreCase);
		System.out.println(list);
		Arrays.sort(new String[]{"afa","ddd"},String::compareToIgnoreCase);
		new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}
			
		};
	}
}
