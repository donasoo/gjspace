package edu.guan.designpatterns.strategy;

public class DuckMallar extends Duck {
	
	public DuckMallar(){
		quackBehavior=new Quack();
		flyBehavior=new FlyWithWings();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("I am a real mallar duck");

	}
	

}
