package wd.java8.dept01;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Example1_2P4 {

	@Test
	public void main(){
		List<Integer>list = new ArrayList<Integer>();
		list.add(1);
		list.add(15);
		list.add(2);
		list.sort((x,y)->Integer.compare(x, y));
		System.out.println(list);
	}
}
