package org.design.pattern.name.factoryP.example01;

import org.design.pattern.name.factoryP.example01.pojo.Circle;
import org.design.pattern.name.factoryP.example01.pojo.Rectangle;
import org.design.pattern.name.factoryP.example01.pojo.Shape;
import org.design.pattern.name.factoryP.example01.pojo.Square;

public class ShapeFactory {
	
	   //ʹ�� getShape ������ȡ��״���͵Ķ���
	   public Shape getShape(String shapeType){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("CIRCLE")){
	         return new Circle();
	      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
	         return new Rectangle();
	      } else if(shapeType.equalsIgnoreCase("SQUARE")){
	         return new Square();
	      }
	      return null;
	   }
}