package org.tarena.note.dao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.User;

public class NoteBookMapperDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteBookMapperDao bookDao = 
			ac.getBean("noteBookMapperDao",NoteBookMapperDao.class);
		List<NoteBook> list = bookDao.findByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(NoteBook book : list){
			System.out.println(book.getCn_notebook_id()+" "+book.getCn_notebook_name());
		}
	}

}
