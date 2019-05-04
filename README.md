### 디자인패턴 공부
src 폴더에서 디자인패턴을 확인할 수 있음.

### - Delegate pattern: 
델리게이트 패턴은 추상화가 인터페이스 객체를 갖음으로 인해, 구현된 클래스를 인터페이스에 집어 넣어 다양하게 객체를 사용할 수 있었다.

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
5252... 옵저버 패턴은 가장 많이 보는 디자인패턴이 아닐지도...? 안드로이드 프로젝트를 하다 보면 RxJava, LiveData 등에서 사용되어지는 옵저버패턴은 Subject가 Observer들에게 데이터를 제공해주면, Observer들은 해당 데이터를 가공하는 역할을 한다.

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

