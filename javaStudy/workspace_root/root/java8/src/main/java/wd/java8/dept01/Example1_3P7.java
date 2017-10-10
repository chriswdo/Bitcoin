package wd.java8.dept01;

import java.util.function.BiFunction;

import org.junit.Test;

public class Example1_3P7 {

	/**
	 * 保存lambda表达式
	 */
	@Test
	public void main(){
		BiFunction<String,String,Integer>comp = (first,second)-> Integer.compare(first.length(),second.length());
	}
}
