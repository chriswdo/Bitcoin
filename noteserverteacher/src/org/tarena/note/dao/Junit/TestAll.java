package org.tarena.note.dao.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
//����suite�׼�����ִ�ж��Test��
@RunWith(Suite.class)
@SuiteClasses({MyUtilJunitTest.class,UserMapperDaoJunitTest.class})
public class TestAll {

}
