package edu.guan.designpatterns.decorator;

public class Milk extends Condiment {
	
	//Beverage beverage;
	
	public Milk(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage=beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.beverage+" with Milk";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.1+this.beverage.cost();
	}

}
