### 디자인패턴 공부
src 폴더에서 디자인패턴을 확인할 수 있음.

### - Delegate pattern: 
- 델리게이트 패턴은 추상화가 인터페이스 객체를 갖음으로 인해, 구현된 클래스를 인터페이스에 집어 넣어 다양하게 객체를 사용할 수 있었다.
- 특징 : 상속을 받는 대신, 행동을 부여받음으로 인해서 객체에서 인터페이스를 통해서 원하는 행동을 할 수 있도록 유도하는 패턴.  
1.1. 추상화 클래스로부터 인터페이스 객체를 받음. 이 때, 인터페이스를 상속받는 클래스가 들어오기 때문에, 가변적인 클래스의 행위를 처리할 수 있음.
~~~
abstract class Car{
	Engine engine;
	Window window;
	
	void start() {
		engine.drive();
	}
	
	void stop() {
		engine.stop();
	}
	
	void openWindow() {
		window.open();
	}
}
~~~

1.2. 클래스는 SuperEngine,BeginnerEngine, GoldEngine등이 나올 수 있지만, 인터페이스를 통해서 하나의 객체로 관리하지만, 다른 결과를 제공해줄 수 있도록 함.

~~~
interface Engine{
	void drive();
	void stop();
}

interface Window{
	void open();
	void close();
}
~~~

1.3. 추상화된 객체를 상속받는 클래스. 생성자를 통해서 내부에서 사용되는 변수들이 Super,Gole,Beginner등의 속성을 갖게 되며, 이것은 해당 클래스를 가변적으로 사용할 수 있게함. 



~~~
class SuperCar extends Car{
	SuperCar(){
		engine = new SuperEngine();
		window = new CleanWindow();
	}
}

class BeginnerCar extends Car{
	BeginnerCar(){
		engine = new BeginnerEngine();
		window = new CleanWindow();
	}
}
~~~


### - Observer Pattern :
- 5252... 옵저버 패턴은 가장 많이 보는 디자인패턴이 아닐지도...? 안드로이드 프로젝트를 하다 보면 RxJava, LiveData 등에서 사용되어지는 옵저버패턴은 Subject가 Observer들에게 데이터를 제공해주면, Observer들은 해당 데이터를 가공하는 역할을 한다.
- 특징 : Subject로 하여, 옵저버를 등록해서 갖고 있으며, Subject는 옵저버에게 데이터를 전달하고, 옵저버는 받은 데이터를 처리하는 패턴.

2.1. Interface 소개

- Subject는 observer를 register하고, watchPeople을 통해 데이터를 받고, notifyObserverData를 통해 옵저버에게 데이터가 변했다는 것을 알린다. 
- Observer는 notifyObserverData에서 Observer들에게 데이터를 send할 때 사용.
- DisplayInterface는 Observer에서 데이터를 받을 때, 처리하는 행위


~~~
interface Subject{
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObserverData();
	void watchPeople(String peopleName, String behavior);
}

interface Observer{
	public void sendData(String peopleName, String behavior);
}

interface DisplayInterface{
	public void display();
}
~~~

2.2 클래스

- CCTV객체는 재미삼아 싱글톤으로 만들었음. observer를 register하면, arrayList로 관리를 하고, watchPeople이 일어나는 시점으로 CCTVManager들에게 데이터가 변했다는 것을 알림.
- CCTVManager객체는 CCTV가 sendData시에 display를 하는 역할을 함.

~~~
class CCTV implements Subject{
	//싱글톤 객체로 유일한 Subject를 만듦.
	public static CCTV cctvInstance = new CCTV();
	private ArrayList<Observer> cctvObserverArray = new ArrayList();
	private String peopleName;
	private String behavior;
	
	//singleTon constructor
	private CCTV() {
		
	}
	
	//singleTon maker
	public static CCTV getInstance() {
		return cctvInstance;
	}
	
	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		cctvObserverArray.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		cctvObserverArray.remove(observer);
	}

	@Override
	public void notifyObserverData() {
		for(Observer cctvObserver : cctvObserverArray) {
			cctvObserver.sendData(peopleName, behavior);
		}
	}

	@Override
	public void watchPeople(String peopleName, String behavior) {
		this.peopleName = peopleName;
		this.behavior = behavior;
		notifyObserverData();
	}
}

class CCTVManager implements DisplayInterface , Observer{
	private int cctvIndex;
	private String peopleName;
	private String behavior;
	CCTVManager(int cctvIndex){
		this.cctvIndex = cctvIndex;
	}
	@Override
	public void sendData(String peopleName, String behavior) {
		this.peopleName = peopleName;
		this.behavior = behavior;
		display();
	}

	@Override
	public void display() {
		System.out.println(String.valueOf(cctvIndex)+"번 cctv가 포착했습니다.\n"+peopleName +"님이 "+behavior+"을 하고 있습니다.");
	}
	
}
~~~


### - Decorator Pattern :
- 해당 패턴은 new ClassOne(new ClassTwo(new ClassThree()));와 같은 패턴을 통해서 추가적인 값을 얻어내는 패턴입니다. 해당 예제에서는 Drill을 통해서 예제를 만들었는데, SuperDrill + Ruby + Gold로 드릴을 강화해서 값을 축적하는 로직입니다.
- 특징 : 정형화된 객체가 있을 경우, 데코레이터 패턴을 통해서 해당 객체의 값을 축적하거나, 더 용이하게 확장할 수 있는 패턴.

3.1. 추상 클래스

- 추상 클래스를 통해서 기본적인 클래스 틀을 만든다.
- Drill에서는 itemName의 값을 얻어옵니다.
- power()나 getItemName() 매소드를 통해서 상속받는 클래스에서 해당 매소드를 구현하고, 임의로 커스텀을 하도록 유도한다.

~~~

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
~~~

3.2. 구현된 클래스

- SuperDrill는 근본이 되는 객체로, 특정 값(itemName, power)들을 고유적으로 갖고 있다.
- BorkenDrill는 근본이 되는 객체로, 특정 값(itemName, power)들을 고유적으로 갖고 있다.
- Ruby는 Drill들을 받게 되어, 해당 값을 축적해서 값을 쌓는 객체이다.
- Gold는 Drill들을 받게 되어, 해당 값을 축적해서 값을 쌓는 객체이다.

그래서, 구조는 SuperDrill이 먼저 만들어진다.
SuperDrill superDrill -> superDrill = Ruby(superDrill); -> superDrill = Gold(superDrill);로 해서 값이 계속해서 데코레이트 되게 된다.
~~~
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
~~~

### - Factory Pattern :
- 객체를 생성하는 행위는 코드를 작성할 때 유연성을 더디게 한다. 팩토리 패턴을 하는 이유는 값에 따라서, 객체들을 다수 생성해야하는 경우, 해당 코드들을 캡슐화하여 팩토리 패턴으로 묶어서 관리하는 것을 의미한다. 또한 여기에는 팩토리 매소드 패턴과 추상 팩토리 패턴이 있다.
- 팩토리 매소드 패턴 : abstract class 내부에 create() 매소드를 abstract로 제공하여, 상속받는 클래스에서 create()를 직접 구현해서, 객체를 생성하는 코드를 클래스에 위임하는 패턴이다.
- 추상 팩토리 패턴 : 만들어지는 객체에 팩토리를 넣어서, 객체의 변수값들을 직접 만들어주도록 관리하는 구조이다.
AFactory, BFactory => Coffee(AFacotory) , Coffee(BFactory) => AFactoryCoffee , BFactoryCoffee
그래서 해당 변수 값들이 Factory에 의해 만들어지는 구조이다.

4.1. 팩토리 매소드 패턴 - 추상 클래스 
- 팩토리 매소드 패턴은 abstract class로 하여금, 베이스가 되는 클래스를 만들게 되고, create를 abstract화 시켜서, 상속을 받게 되는 클래스에서 해당 매소드를 사용하여, 팩토리를 구현시키는 구조이다.

~~~
abstract class CoffeeMachine{
	abstract Coffee create(CoffeeType coffeeType);
}
~~~

4.2. 팩토리 매소드 패턴 - 클래스

- CoffeeType의 조건에 따라서 클래스 내부에서 객체를 생성하는 구조이다. 간단하게, 생성하는 로직을 캡슐화하여 클래스에 넣어준 것이다.

~~~
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
~~~

4.3. 팩토리 매소드 패턴 - 생성되어지는 객체

- Coffee의 abstract class를 기반으로 각종 커피객체를 생성

~~~
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
~~~

4.4. 팩토리 매소드 패턴 - 메인 코드

- 커피머신을 호출한 후에, create매소드에 type인자를 넣어주게 되면 만들어진다. 간단한 구조이다.

~~~
public class FactoryMethodPattern {
	public static void main(String[] args) {
		CoffeeMachine goldCoffeeMachine = new GoldCoffeeMachine();
		Coffee americano = goldCoffeeMachine.create(CoffeeType.Americano);
		System.out.println(americano.getName());
	}
}
~~~

### - SingleTon Pattern

5.1. 인터페이스
- CoffeeFactory는 해당 팩토리를 상속받는 클래스에서 별도로 CoffeeRecipe에 있는 변수값들을 만들어주도록 하는 매소드를 제공한다.
- CoffeeRecipe는 모델과 같으며, 다양한 객체에서 CoffeeRecipe를 상속받을 때, Factory로 하여금 변수가 삽입되는 구조를 만들도록 한다.

~~~

interface CoffeeFactory{
	String makeCoffee();
}

abstract class CoffeeRecipe{
	String coffee;
	
	abstract String getCoffee();
}
~~~

5.2. 클래스

- CoffeeFactory를 상속받은 Gold,House CoffeeFactory는 변수로 하여금, 자신들만의 값을 만든다.
- BlackCoffeeRecipe의 생성자에는 CoffeeFactory를 받게 되는데, 이를 통해서 Gold,House등 다양한 CoffeeFactory가 들어오게 되고, getCoffee() 매소드 에서는 팩토리에서 coffee의 종류를 받게 된다.

~~~
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
~~~

5.3. 메인 코드

- 간단하게 팩토리를 생성에서 레시피에 팩토리를 넣어주고, 커피의 종류를 확인하는 예제이다.

~~~
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
~~~

### -싱글톤 패턴 :
- 5252... 아까 가장 많이 쓰는 패턴이라는 말은 취소하겠습니다만... 싱글톤 패턴은 정말 많이 사용하는 패턴이며, 단순하지만 복잡한 패턴이다.
- 안드로이드에서는 프레그먼트의 홀더를 만들거나 DB를 관리하는 객체, 네트워크 객체를 싱글톤으로 만들어서 관리하게 되면 무거운 객체를 여러개 만드는 것을 방지하기에 많은 이점을 가져다 주는 것으로 알고 있다.
- 하지만, 중요한 것은 멀티 스레드 환경에서 일반적인 싱글톤 생성방법은 위험하다.
~~~
Thread 1 : o if(isObjectNull)                     o 객체 생성            
Thread 2 :                    o if(isObjectNull)             o 객체 생성
~~~
- 위와 같이 다중 스레드에서 싱글톤이 여러개 만들어져서 의미를 상실하는 경우를 방지하고자, 스레드를 생성하는 방법을 확실히 알고 있으면 좋다.


6.1. 일반적인 싱글톤
- 일반적으로 객체가 null일 경우, singleTon을 생성하는 로직의 SingleTon 클래스이다.
- 생성자를 private으로 처리해서 외부에서 new를 통해서 직접 구현을 못 하게 막은 것이 특징이다.
- 병렬 스레드에서는 해당 싱글톤으로 객체를 생성할 경우에, null check 부분에서 여러 개의 스레드가 들어와서 객체가 생성되는 경우가 있다고 한다.
- 앱 개발을 하면서 해당 경우를 경험하기는 힘들었지만, 많은 사람들이 알고 있는 현상이기 때문에 싱글톤을 만들 때 정확한 방법을 숙지하고 싶었고, 2번과 3번 방법으로 객체를 생성하면 된다고 한다.

~~~
class SingleTon1{
	private static SingleTon1 singleTon;
	
	private SingleTon1() {
		System.out.println("make singleton 1!");
	}
	
	public static SingleTon1 getInstance() {
		if(singleTon == null) {
			singleTon = new SingleTon1();
		}
		return singleTon;
	}
}
~~~
6.2. 객체 생성과 함께 하는 싱글톤
- SingleTon의 인스턴스를 미리 생성하는 코드이다. Adapter에 ArrayList를 미리 생성하더라도 null이 나오지 않는 것과 동일한 것이라고 생각한다.
- 6.1은 안정성의 문제, 6.3은 속도적인 측면에서 문제가 있다고 하지만, 해당 코드에 대해서 언급은 없다. 시간이 없는 관계로, 기회가 되면 찾아봐야겠다.

~~~
class SingleTon2{
	private static SingleTon2 singleTon = new SingleTon2();
	
	private SingleTon2() {
		System.out.println("make singleton 2!!");
	}
	
	public static SingleTon2 getInstance() {
		return singleTon;
	}
}
~~~

6.3. DCL과 함께 하는 싱글톤
- synchronized를 통해서 객체가 멀티스레드로부터 접근되는 것을 방지하는 것을 의미한다. 그리고 이전 6.1 코드와 동일하게 싱글톤 객체의 null 체크 후, 객체를 생성하는 로직이다.
- 해당 코드는 6.1. 코드에 비해서 속도가 느리다고 한다.

~~~
class SingleTon3{
	private volatile static SingleTon3 singleTon;
	
	private SingleTon3() {
		System.out.println("make singleton 3!!!");
	}
	
	public static SingleTon3 getInstance() {
		if(singleTon == null) {
			synchronized(SingleTon3.class) {
				if(singleTon == null) {
					singleTon = new SingleTon3();
				}
			}
		}
		return singleTon;
	}
}
~~~

6.4. 메인 코드

~~~
public class SingleTonPattern {
	public static void main(String[] args) {
		SingleTon1.getInstance();
		SingleTon2.getInstance();
		SingleTon3.getInstance();	
	}
}
~~~

### - Command Pattern
- 커맨드 패턴은 팩토리 패턴과 비슷한 성격을 갖고 있다고 생각한다. 팩토리 패턴은 생성자 부분을 캡슐화 시켰다면, 커맨드 패턴은 실행부분을 캡슐화 시킨게 이점이다.
- 인터페이스를 상위 객체로 하여, execute(){interface.execute()} 로 하여서 개발자는 가변적인 커맨드를 실행시키는게 재미있다.

7.1. 인터페이스 
- 간단하게 execute하는 부분밖에 없다.

~~~
interface Command{
	public String execute();
}
~~~

7.2. 클래스 부분 - 다양한 상태값을 커맨드화시킴.
- 스태이트 패턴에 착안을 한다면, 어느 정도 비슷한 것 같다.

~~~
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
~~~
7.3. 컨트롤러
- 컨트롤러는 Command를 설정하게 되면, command.execute();에 의해서 다양한 객체가 들어올 수 있도록 처리했고, 이를 통해서 유연한 코드를 작성할 수 있도록 도와준다.
- 해당 코드에서는 CoffeeController의 물의 온도를 받는 예제를 통해서 이해를 돕는다.
~~~
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
~~~
7.4.메인 코드
- 커맨드를 다양하게 만든다.
- 컨트롤러를 만들어서 커맨드를 입히고, 해당 커맨드를 실행하고, 온도를 체크하는 로직이다.

~~~
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
~~~
