package edu.guan.designpatterns.strategy;

public class Simulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Duck mallard=new DuckMallar();
		mallard.fly();
		mallard.quack();
		
		Duck model=new DuckModel();
		model.fly();
		model.quack();

	}

}
