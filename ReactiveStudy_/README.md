### 리액티브 프로그래밍
- 해당 코드들은 슈도코드로, 실제로 빌드 되지는 않습니다.

### funcitonal interface : 
- 함수형 인터페이스는 인터페이스의 구조에 한 개의 매소드를 갖고 있는 것을 의미한다.
- 이를 통해서 우리는 익명함수, 람다식을 만들어서 사용할 수 있다.

### anonymous method : 
- 익명함수는 functional interface로 나오는 동시에, 해당 코드에서만 사용되어지는 매소드이기도 하다.
- 일급객체 => 모든 매소드, 변수 등은 객체로 사용되어 진다.
- 고차함수 => 함수를 변수로 받을 수 있는 것을 의미한다.

### lambda :
- 람다식은 대게 functional interface의 anonymous method로부터 출발한다.
- 스코프적인 관점에서는 닫힌 변수(내부 변수)들을 통해서 특정 행위를 처리하는 것을 의미한다.
- btn.setOnClickListener를 생각하면 편하다.

### closure :
- 스코프적인 관점에서 외부 변수들을 취합해서 닫혀진 함수처럼 사용하는 것을 의미한다.
- 예를 들어, 전역변수인 a가 있을 경우, a를 참조해서 closure 내부의 매소드를 처리한다.
- 하지만, 외부의 전역변수는 final로 인식해서 쉽게 변경될 수 없다.

### lambda vs closure : 
- 스코프적인 관점으로 람다는 내부 변수를 통해서 행위를 처리하고, 클로저는 외부 변수를 받아와서 행위를 처리한다.
- 람다는 인터페이스를 만들어서 변수를 받기 때문에, 내부 변수를 처리하는 것이 용이하다.
- 코틀린에서는 클로저를 통해 외부 변수를 변경할 수 있다.

java_closure.png
kotlin_closure.png

### HotObservable :
- 실행의 권한은 Observable에 있음.
- 구독을 하지 않더라도 자동적으로 실행되는 것을 볼 수 있음.
- ConnectObservable이 publish()된 후에, 해당 객체를 connect()를 통해서 HotObservable을 볼 수 있다.
- 현실 세계의 예제로는 Live 방송을 많이 비교함.

### ColdObservable :
- 실행의 권한은 구독자에 있음.
- 구독을 하게 되면 처음부터 값을 얻음.
- 대부분의 rx는 cold이다.
