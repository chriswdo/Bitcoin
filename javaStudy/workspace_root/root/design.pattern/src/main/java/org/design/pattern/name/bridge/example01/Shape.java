package org.design.pattern.name.bridge.example01;

public abstract class Shape {
	protected int x,y,redius;
	   protected DrawAPI drawAPI;
	   protected Shape(DrawAPI drawAPI){
	      this.drawAPI = drawAPI;
	   }
	   public abstract void draw();	
}
