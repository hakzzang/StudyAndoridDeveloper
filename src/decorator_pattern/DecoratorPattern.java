package decorator_pattern;

public class DecoratorPattern {
	public static void main(String[] args) {
		Drill superDrill = new SuperDrill();
		System.out.println(superDrill.getItemName());
		System.out.println("Power:"+superDrill.power());
		superDrill = new Ruby(superDrill);
		System.out.println(superDrill.getItemName());
		System.out.println("Power:"+superDrill.power());
		superDrill =new Gold(superDrill);
		System.out.println(superDrill.getItemName());
		System.out.println("Power:"+superDrill.power());
	}
}

class SuperDrill extends Drill{
	SuperDrill(){
		itemName ="Super Drill::";
	}
	
	@Override
	public double power() {
		return 100;
	}
}

class BrokenDrill extends Drill{
	BrokenDrill(){
		itemName = "Broken Drill::";
	}

	@Override
	public double power() {
		// TODO Auto-generated method stub
		return 1;
	}
}



class Ruby extends Enchant{
	Drill drill;
	Ruby(Drill drill){
		this.drill = drill;
	}
	
	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return drill.getItemName() +"루비::";
	}

	@Override
	public double power() {
		// TODO Auto-generated method stub
		return drill.power() + 200.0 ;
	}
	
}

class Gold extends Enchant{
	Drill drill;
	Gold(Drill drill){
		this.drill = drill;
	}
	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return drill.getItemName() +"골드::";
	}
	@Override
	public double power() {
		// TODO Auto-generated method stub
		return drill.power() + 1000.0;
	}
}

abstract class Drill{
	String itemName;
	
	public String getItemName() {
		return itemName;
	}
	
	public abstract double power();
}

abstract class Enchant extends Drill{
	public abstract String getItemName();
}