package command_pattern;

//특징은 Factory패턴이 생성 부분을 캡슐화 했다면
//커맨트 패턴은 실행 부분을 캡슐화를 통해
//유연하게 객체를 실행하는 것이 특징이다.
public class CommandPattern {
	public static void main(String[] args) {
		//다양한 커맨드를 사용하는 것은 상태를 조절하는 것을 의미하기도 한다.
		Command noneCommand = new NoneCommand();
		Command normalCommand = new NormalCommand();
		Command coldCommand = new ColdCommand();
		Command hotCommand = new HotCommand();
		
		CoffeeController coffeeController = new CoffeeController();
		coffeeController.setCommand(noneCommand);
		coffeeController.executeCommand();
		System.out.println(coffeeController.getWaterOfTemperature());

		coffeeController.setCommand(normalCommand);
		coffeeController.executeCommand();
		System.out.println(coffeeController.getWaterOfTemperature());
		
		coffeeController.setCommand(coldCommand);
		coffeeController.executeCommand();
		System.out.println(coffeeController.getWaterOfTemperature());
		
		coffeeController.setCommand(hotCommand);
		coffeeController.executeCommand();
		System.out.println(coffeeController.getWaterOfTemperature());
	}
}

class CoffeeController{
	Command command;
	String waterOfTemperature;
	CoffeeController(){
		
	}
	
	void setCommand(Command command) {
		this.command = command;
	}
	
	void executeCommand() {
		waterOfTemperature = command.execute();
	}
	
	String getWaterOfTemperature(){
		return waterOfTemperature;
	}
}

class HotCommand implements Command{
	@Override
	public String execute() {
		return "Temperature is 100.";
	}	
}

class ColdCommand implements Command{
	@Override
	public String execute() {
		return "Temperature is -10.";
	}
}

class NormalCommand implements Command{
	@Override
	public String execute() {
		return "Temperature is 30.";
	}
}

class NoneCommand implements Command{
	@Override
	public String execute() {
		return "Empty Command";
	}
}

interface Command{
	public String execute();
}
