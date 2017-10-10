package wd.java8.dept03;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;

public class Example3_5P57 {

	public void main(){
		Image image = new Image("C:/Users/Public/Pictures/Sample Pictures/灯塔.jpg");
//		Image image2 = transform(image,Color::brighter);
	}
	
	public <T> T transform(T image , UnaryOperator<T> operator){
		return  operator.apply(image);
	}
}
