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
