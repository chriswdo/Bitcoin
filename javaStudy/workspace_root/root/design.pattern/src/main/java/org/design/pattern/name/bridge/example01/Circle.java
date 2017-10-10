package org.design.pattern.name.bridge.example01;

public class Circle extends Shape{
	protected Circle(int x,int y,int redius,DrawAPI drawAPI) {
		super(drawAPI);
		this.x=x;
		this.y=y;
		this.redius=redius;
	}

	@Override
	public void draw() {
		drawAPI.drawCircle(x, y, redius);
		
	}

}
