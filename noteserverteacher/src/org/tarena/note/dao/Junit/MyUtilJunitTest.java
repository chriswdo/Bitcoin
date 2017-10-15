package org.tarena.note.dao.Junit;

import org.junit.Assert;
import org.junit.Test;
import org.tarena.note.util.MyUtil;



public class MyUtilJunitTest {
	/**
	 *��������1 
	"abc"
	"def"
	"ABCDEF"
	 */
	@Test
	public void test1(){
		String actual = MyUtil.add("abc", "def");
		Assert.assertEquals("ABCDEF", actual);
	}
	
	/**
	 *��������1 
	"ab c"
	" d ef "
	"ABCDEF"
	 */
	@Test
	public void test2(){
		String actual = MyUtil.add("ab c", " d ef ");
		Assert.assertEquals("ABCDEF", actual);
	}
	
	/**
	 *��������3 
	"AB c"
	" d eF "
	"ABCDEF"
	 */
	@Test
	public void test3(){
		String actual = MyUtil.add("AB c", " d eF ");
		Assert.assertEquals("ABCDEF", actual);
	}
	
	/**
	 *��������3 
	""
	"d eF "
	"DEF"
	 */
	@Test
	public void test4(){
		String actual = MyUtil.add("", " d eF ");
		Assert.assertEquals("DEF", actual);
	}
	
	/**
	 *��������3 
	"ab C"
	""
	"ABC"
	 */
	@Test
	public void test5(){
		String actual = MyUtil.add("ab C", "");
		Assert.assertEquals("ABC", actual);
	}
	
	/**
	 *��������3 
	"ab C"
	null
	"ABC"
	 */
	@Test
	public void test6(){
		String actual = MyUtil.add("ab C", null);
		Assert.assertEquals("ABC", actual);
	}
	
	/**
	 *��������3 
	null
	"de f"
	"DEF"
	 */
	@Test
	public void test7(){
		String actual = MyUtil.add(null, "de f");
		Assert.assertEquals("DEF", actual);
	}
	
	/**
	 *��������3 
	null
	null
	""
	 */
	@Test
	public void test8(){
		String actual = MyUtil.add(null, null);
		Assert.assertEquals("", actual);
	}
}
