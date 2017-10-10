package wd.java8.dept02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * map 和 flatmap的区别
 * @author wd
 *
 */
public class Example2_3P26 {

	@Test
	public void main(){
		List<String>list = new ArrayList<String>();
		list.add("abc");
		list.add("def");
		
		Stream<Stream<Character>> ret = list.stream().map(Example2_3P26::generateCharactorStream);
		System.out.println(ret);
		
		Stream<Character> ret_flat = list.stream().flatMap(Example2_3P26::generateCharactorStream);
	}
	
	public static Stream<Character> generateCharactorStream(String s){
		List<Character> list = new ArrayList<Character>();
		for(Character c : s.toCharArray())list.add(c);
		return list.stream();
	}
}
