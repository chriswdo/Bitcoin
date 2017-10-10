package org.design.pattern.name.BuilderP.example01;

public abstract class Burger implements Item{
	public Packing packing(){
		return new Wrapper();
	}
}
