package org.tarena.note.dao.Junit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class UserMapperDaoJunitTest {
	private UserMapperDao userDao;
	
	@Before//ÿ�ε���test������ִ��һ��
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
	}
	
	/**
	 * ��������1
	 * ����demo
	 * ����user����Ϊnull
	 */
	@Test
	public void test1(){
		User user = userDao.findByName("demo");
		//ʹ�ö��Ի����ж�user��Ϊnull
		Assert.assertNotNull(user);
	}
	
	/**
	 * ��������2
	 * ����demo1
	 * ����user����Ϊnull
	 */
	@Test
	public void test2(){
		User user = userDao.findByName("demo1");
		//ʹ�ö��Ի����ж�userΪnull
		Assert.assertNull(user);
	}
	
}
