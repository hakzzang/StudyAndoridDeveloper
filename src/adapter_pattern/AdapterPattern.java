package adapter_pattern;

public class AdapterPattern {
	public static void main(String[] args) {
		WaterMachine waterMachine = new NormalWaterMachine();
		waterMachine.makeWater();
		CoffeeMachine coffeeMachine = new SuperCoffeeMachine();
		coffeeMachine.makeCoffee();
		CoffeeMachine coffeeMahcineAdapter = new CoffeeMachineAdapter(waterMachine);
		coffeeMahcineAdapter.makeCoffee();
	}
}

class CoffeeMachineAdapter implements CoffeeMachine{
	WaterMachine waterMachine;
	CoffeeMachineAdapter(WaterMachine waterMachine){
		this.waterMachine = waterMachine;
	}
	
	@Override
	public void makeCoffee() {
		waterMachine.makeWater();
		addCoffeeBeans();
	}
	
	void addCoffeeBeans() {
		System.out.println("change water to coffee!!");
	}
}

class NormalWaterMachine implements WaterMachine{
	@Override
	public void makeWater() {
		System.out.println("make water");
	}
}



class SuperCoffeeMachine implements CoffeeMachine{
	@Override
	public void makeCoffee() {
		System.out.println("make cofee");
	}
}

interface WaterMachine{
	void makeWater();
}

interface CoffeeMachine{
	void makeCoffee();
}