package org.tarena.note.util;

public class MyUtil {
	/**
	 * 将两个字符串去掉空格,转成大写连接
	 * @param a 参数1
	 * @param b 参数2
	 * @return 连接结果
	 */
	public static String add(String a,String b){
		if(a==null){
			a="";
		}
		if(b==null){
			b="";
		}
		String a1 = a.trim().toUpperCase().replace(" ", "");
		String b1 = b.trim().toUpperCase().replace(" ", "");
		return a1+b1;
	}

	
	
	
}
