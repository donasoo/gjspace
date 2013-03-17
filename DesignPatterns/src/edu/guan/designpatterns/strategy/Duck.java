package edu.guan.designpatterns.strategy;

public abstract class Duck {

	protected FlyBehavior flyBehavior;
	protected QuackBehavior quackBehavior;
	
	public abstract void display();
	
	public void fly(){
		flyBehavior.fly();
	}
	
	public void quack(){
		quackBehavior.quack();
	}
	
	public void swim(){
		System.out.println("all ducks float");
	}

}
