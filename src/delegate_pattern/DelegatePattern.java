package delegate_pattern;

//2019.05.xx 18:14 : 너무 졸리다.
//책 다 읽고, 예제파일 만들려고 한다...
// by gamjatwigim
//Deletgate Pattern은 abstract class가 interface를 갖고 있는다.
//그리고 클래스를 만드는 과정에서 abstract class or interface를 상속받으며
//해당 클래스에서 인터페이스의 매소드를 호출해준다.
//그러면 특성 인터페이스 속성을 갖는 객체를 만들 수 있게 된다.
public class DelegatePattern {
	public static void main(String args[]) {
		Car superCar = new SuperCar();
		superCar.openWindow();
		superCar.start();
		superCar.stop();
		
		Car beginnerCar = new BeginnerCar();
		beginnerCar.openWindow();
		beginnerCar.start();
		beginnerCar.stop();
	}
}

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

interface Engine{
	void drive();
	void stop();
}

interface Window{
	void open();
	void close();
}

class CleanWindow implements Window{
	@Override
	public void open() {
		System.out.println("Wow open!!");
		
	}

	@Override
	public void close() {
		System.out.println("Wow Close!!");
	}
}

class SuperEngine implements Engine{
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("Super Drive!!");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Super Stop!!");
	}
}

class BeginnerEngine implements Engine{
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("drive...");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("stop..");
	}
	
}