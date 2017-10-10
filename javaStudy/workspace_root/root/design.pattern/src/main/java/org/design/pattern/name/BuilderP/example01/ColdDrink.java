package org.design.pattern.name.BuilderP.example01;

public abstract  class ColdDrink implements Item {
	public Packing packing(){
		return new Bottle();
	}
}
