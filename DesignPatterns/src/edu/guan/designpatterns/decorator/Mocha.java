package edu.guan.designpatterns.decorator;

public class Mocha extends Condiment {
	//Beverage beverage;
	
	public Mocha(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage=beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.beverage.getDescription()+" with Mocha";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.20+this.beverage.cost();
	}

}
