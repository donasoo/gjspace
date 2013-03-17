package edu.guan.designpatterns.decorator;

public class Soy extends Condiment {
	
	//Beverage beverage;
	
	public Soy(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage=beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.beverage.getDescription()+" with soy";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 0.15+this.beverage.cost();
	}

}
