package wd.java8.dept01;

public class Example1_6P12 {
	
	 
	public void main(){
		int matches = 0;
		new Thread( ()->{ System.out.println(matches);}  ).start();
		//这样会报错     matches是自由变量    编译器会禁止局部函数的自由变量的改变，但是不能防止实例变量和静态变量的情况
//		new Thread( ()->{ System.out.println(matches++);}  ).start();;
	}
}
