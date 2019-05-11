package factory_method_pattern;

public class AbstractFactoryPattern {
	public static void main(String[] args) {
		CoffeeFactory goldCoffeeFactory = new GoldCoffeeFactory();
		CoffeeFactory houseCoffeeFactory = new HouseCoffeeFactory();
		CoffeeRecipe blackCoffee = new BlackCoffeeRecipe(goldCoffeeFactory);
		String coffee = blackCoffee.getCoffee();
		System.out.println("삥뽕~~ : "+coffee);
		blackCoffee = new BlackCoffeeRecipe(houseCoffeeFactory);
		coffee = blackCoffee.getCoffee();
		System.out.println("삥뽕~~ : "+coffee);
	}
}

class GoldCoffeeFactory implements CoffeeFactory{
	public String makeCoffee() {
		return "GOLD";
	}	
}

class HouseCoffeeFactory implements CoffeeFactory{
	public String makeCoffee() {
		return "HOUSE";
	}	
}


class BlackCoffeeRecipe extends CoffeeRecipe {
	CoffeeFactory coffeeFactory;
	BlackCoffeeRecipe(CoffeeFactory coffeeFactory){
		this.coffeeFactory = coffeeFactory;
	}

	@Override
	String getCoffee() {
		return coffeeFactory.makeCoffee() +" BLACK COFFEE";
	}
}

interface CoffeeFactory{
	String makeCoffee();
}

abstract class CoffeeRecipe{
	String coffee;
	
	abstract String getCoffee();
}