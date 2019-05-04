package factory_method_pattern;

public class FactoryMethodPattern {
	public static void main(String[] args) {
		CoffeeMachine goldCoffeeMachine = new GoldCoffeeMachine();
		Coffee americano = goldCoffeeMachine.create(CoffeeType.Americano);
		System.out.println(americano.getName());
	}
}

enum CoffeeType{
	Black, Americano, Tea;
}

class GoldCoffeeMachine extends CoffeeMachine{
	@Override
	Coffee create(CoffeeType coffeeType) {
		switch(coffeeType) {
			case Black: 
				return new BlackCoffee();
			case Americano:
				return new AmericanoCoffee();
			case Tea:
				return new Tea();
			default:
				return new NormalCoffee();
		}
	}	
}

abstract class CoffeeMachine{
	abstract Coffee create(CoffeeType coffeeType);
}

class NormalCoffee extends Coffee{
	@Override
	String getName() {
		return "MAXAM";
	}
}

class BlackCoffee extends Coffee{
	@Override
	String getName() {
		return "BLACK COFFEE";
	}	
}

class AmericanoCoffee extends Coffee{
	@Override
	String getName() {
		return "Ame~~Ame~~Ame~~";
	}	
}

class Tea extends Coffee{
	@Override
	String getName() {
		return "T...";
	}	
}

abstract class Coffee{
	abstract String getName();
}