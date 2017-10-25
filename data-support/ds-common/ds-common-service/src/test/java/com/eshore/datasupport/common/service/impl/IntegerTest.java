package com.eshore.datasupport.common.service.impl;

import org.junit.Test;

public class IntegerTest {

	@Test
	public void main(){
		Integer i = 1;
		test(i);
		System.out.println(i);
	}
	
	public void test(Integer i){
		i=i+100;
	}
}
