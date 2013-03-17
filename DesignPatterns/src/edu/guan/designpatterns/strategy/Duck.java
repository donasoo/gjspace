package edu.guan.designpatterns.strategy;

public abstract class Duck {

	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck(){
		display();
	}
	
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
