/**
 * 
 */
package org.design.pattern.name.abstractFactory.example01;

/**
 * @author ymtwd
 *
 */
public abstract interface AbstractFactory {
	   abstract Color getColor(String color);
	   abstract Shape getShape(String shape) ;
}