package edu.guan.designpatterns.decorator;

public class DarkRoast extends Beverage {
	
	public DarkRoast() {
		// TODO Auto-generated constructor stub
		description="Dark Roast";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.99;
	}

}
