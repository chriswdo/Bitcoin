package org.design.pattern.name.singletonP;
/**
 * �������صĵ���ģʽ
 * @author ymtwd
 *
 */
public class SingleObject {
	private static SingleObject instance = new SingleObject();
	private SingleObject(){};
	public static SingleObject getInstance(){
		return instance;
	}
	public void showMessage(){
		
	}
}
