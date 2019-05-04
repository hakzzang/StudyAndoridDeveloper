package observer_pattern;

import java.util.ArrayList;
import java.util.Iterator;

public class ObserverPattern {
	public static void main(String[] args) {
		CCTV cctv = CCTV.getInstance();
		CCTVManager cctvManager = new CCTVManager(1);
		CCTVManager cctvManager2 = new CCTVManager(2);
		CCTVManager cctvManager3 = new CCTVManager(3);
		cctv.registerObserver(cctvManager);
		cctv.registerObserver(cctvManager2);
		cctv.watchPeople("MoonByeongHak", "Coding");
		cctv.watchPeople("MoonByeongHak", "coffee time");
		cctv.removeObserver(cctvManager2);
		cctv.watchPeople("Shirimp burger", "foo~~");
	}
}

class CCTV implements Subject{
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
		// TODO Auto-generated method stub
		for(Observer cctvObserver : cctvObserverArray) {
			cctvObserver.sendData(peopleName, behavior);
		}
	}

	@Override
	public void watchPeople(String peopleName, String behavior) {
		// TODO Auto-generated method stub
		this.peopleName = peopleName;
		this.behavior = behavior;
		notifyObserverData();
	}
}

class CCTVManager implements ObserverIterator , Observer{
	private int cctvIndex;
	private String peopleName;
	private String behavior;
	CCTVManager(int cctvIndex){
		this.cctvIndex = cctvIndex;
	}
	@Override
	public void sendData(String peopleName, String behavior) {
		// TODO Auto-generated method stub
		this.peopleName = peopleName;
		this.behavior = behavior;
		display();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(String.valueOf(cctvIndex)+"번 cctv가 포착했습니다.\n"+peopleName +"님이 "+behavior+"을 하고 있습니다.");
	}
	
}
interface ObserverIterator{
	void display();
}

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