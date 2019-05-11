package singleton_pattern;

public class SingleTonPattern {
	public static void main(String[] args) {
		SingleTon1.getInstance();
		SingleTon2.getInstance();
		SingleTon3.getInstance();	
	}
}

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

class SingleTon2{
	private static SingleTon2 singleTon = new SingleTon2();
	
	private SingleTon2() {
		System.out.println("make singleton 2!!");
	}
	
	public static SingleTon2 getInstance() {
		return singleTon;
	}
}

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