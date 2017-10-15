package org.tarena.note.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplicationContext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
	}

}
