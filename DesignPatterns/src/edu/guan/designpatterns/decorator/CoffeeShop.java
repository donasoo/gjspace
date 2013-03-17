package edu.guan.designpatterns.decorator;

public class CoffeeShop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Beverage coffee1=new Espresso();
		System.out.println("coffee1 is " + coffee1.getDescription());
		System.out.println("coffee1 cost $"+coffee1.cost());
		
		Beverage coffee2=new DarkRoast();
		coffee2=new Mocha(coffee2);
		coffee2=new Mocha(coffee2);
		coffee2=new Whip(coffee2);
		System.out.println("coffee2 is " + coffee2.getDescription());
		System.out.println("coffee2 cost $"+coffee2.cost());
		
		Beverage coffee3=new HouseBlend();
		coffee3=new Soy(coffee3);
		coffee3=new Mocha(coffee3);
		coffee3=new Whip(coffee3);
		System.out.println("coffee3 is "+coffee3.getDescription());
		System.out.println("coffee3 cost $"+coffee3.cost());

	}

}
