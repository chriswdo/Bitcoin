package org.tarena.note.dao.Junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class UserMapperDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
		User user = userDao.findByName("demo1");
		if(user != null){
			System.out.println(user.getCn_user_password());
		}else{
			System.out.println("���޴���");
		}
		
	}

}
