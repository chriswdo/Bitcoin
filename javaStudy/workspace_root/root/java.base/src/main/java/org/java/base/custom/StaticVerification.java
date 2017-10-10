package org.java.base.custom;

public class StaticVerification {
	
	public int a;

	public static void test(){
		/*
		 * 静态方法中的内部类，仍然是静态内部类
		 */
		class InnerClass{
			
		};
	}
	
	public void test1(){
		class InnerClass{
			int b=a;
		};
	}
}
