### 디자인패턴 공부
src 폴더에서 디자인패턴을 확인할 수 있음.

- Delegate pattern: 
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


- Observer Pattern :

