package com.eshore.datasupport.metadata.service.impl;

import java.util.Date;

import org.junit.Test;

public class DateClone {

	@Test
	public void main(){
		Date date = new Date();
		Date date1 = (Date) date.clone();
		System.out.println(date1);
	}
}
