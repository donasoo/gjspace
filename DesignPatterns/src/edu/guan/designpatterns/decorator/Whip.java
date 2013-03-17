package edu.guan.designpatterns.decorator;

public class Whip extends Condiment {
	
	//Beverage beverage;
	
	public Whip(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage=beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.beverage.getDescription()+" with whip";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.1+this.beverage.cost();
	}

}
