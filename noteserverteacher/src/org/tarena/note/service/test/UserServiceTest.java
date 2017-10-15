package org.tarena.note.service.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class UserServiceTest {
	public static void main(String[] args) {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		UserService service = ac.getBean(
				"userServiceImpl",UserService.class);
		NoteResult result = 
			service.checkLogin("demo", "c8837b23ff8aaa8a2dde915473ce0991");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
}
