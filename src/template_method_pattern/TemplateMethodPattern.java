package template_method_pattern;

//TemplateMethod패턴은 비슷한 성질은 유지하고, 다른 성질을 따로 구현하는 것을 의미한다.
//abstract의 성질을 이용하는데, 이는 abstract class에서
//abstract method(); 를 선언하게 되면 상속받는 클래스에서 구현해야하는 것을 이용하는게 특징이다.
//또한 hook, 여기 에서는 isPremium이라는 매소드를 기본적인 성질로 구현함과 동시에
//abstract의 특성인 구현하거나, 혹은 구현해도 되지 않는 성질을 이용해 별도로 커스텀하는 것이 특징이다.
public class TemplateMethodPattern {
	public static void main(String[] args) {
		Machine waterMachine = new WaterMachine(false);	
		Machine waterMachineForChild = new WaterMachineForChild();
		waterMachine.makeColdWater();
		waterMachine.makeHotWater();
		waterMachineForChild.makeColdWater();
		waterMachineForChild.makeHotWater();
	}
}
class WaterMachineForChild extends Machine{
	WaterMachineForChild(){
		
	}
	
	@Override
	void makeHotWater() {
		if(isPremium()) {
			System.out.println("clear water...");
		}
		System.out.println("Temerature is 60.");
	}
}

class WaterMachine extends Machine{
	boolean isPremium;
	WaterMachine(boolean isPremium){
		this.isPremium = isPremium;
	}
	
	@Override
	void makeHotWater() {
		if(isPremium()) {
			System.out.println("clear water...");
		}
		System.out.println("Temerature is 100.");
	}
	
	@Override
	public boolean isPremium() {
		return isPremium;
	}
}

abstract class Machine{
	abstract void makeHotWater();
	void makeColdWater() {
		System.out.println("Temerature is -10.");
	}
	
	boolean isPremium() {
		return true;
	}
}