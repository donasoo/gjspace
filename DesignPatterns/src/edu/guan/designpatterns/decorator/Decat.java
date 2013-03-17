package edu.guan.designpatterns.decorator;

public class Decat extends Beverage {
	
	public Decat() {
		// TODO Auto-generated constructor stub
		description="Decat";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 1.05;
	}

}
