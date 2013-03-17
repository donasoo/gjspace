package edu.guan.designpatterns.strategy;

public class QuackMute implements QuackBehavior {

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println("<<silence>>");

	}

}
