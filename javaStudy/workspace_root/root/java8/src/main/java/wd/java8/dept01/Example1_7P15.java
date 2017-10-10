package wd.java8.dept01;

public class Example1_7P15 implements Name,Person {

	@Override
	public long getId() {
		return 0;
	}

	@Override
	public String getName() {
		return Name.super.getName();
	}
	

	
}

interface  Name{
	default String getName(){
		return getClass().getName();
	}
}

interface Person{
	long getId();
	default String getName(){
		return "john";
	}
}