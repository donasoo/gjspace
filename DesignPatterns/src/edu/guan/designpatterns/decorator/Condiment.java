package edu.guan.designpatterns.decorator;

public abstract class Condiment extends Beverage {
	Beverage beverage;
	@Override
	public abstract String getDescription();

}
