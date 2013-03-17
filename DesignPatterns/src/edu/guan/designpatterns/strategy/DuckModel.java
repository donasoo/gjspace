package edu.guan.designpatterns.strategy;

public class DuckModel extends Duck {
	
	public DuckModel(){
		flyBehavior=new FlyNoWay();
		quackBehavior=new Quack();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("I am a model duck");

	}

}
