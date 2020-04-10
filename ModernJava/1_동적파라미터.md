### 공부하는 것은 내 맴
- ModernJavaInAction 책을 읽고, 정리하고자 작성하는 md 파일임을 말하고,
  꼭 해당 책을 읽어봤으면, 하는 생각을 갖고 있습니다. 혹시, 문제가 되는 사항이 있다면,
  알려주시면 감사하겠습니다.
  
### 1. 동적 파라미터화 코드(Behavior Parameterization)
- 동적 파라미터화 코드는 아직 어떻게 실행할 것인지, 결정하지 않은 코드 블록을 의미한다. (p67)
- 동적 파라미터화란 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블록을 의미한다.
``` java
void makeItem(ItemFactory itemFactory){
   List<Item> items = new ArrayList();
   for(int i=0; i< 100; i++){
      items.add(itemFactory.makeItem());
   }
   return items;
}
```
 이해한 바로는, 특정 로직을 어느 정도 추상화시켜서, 해당 메소드를 통해서 어떤 행위를 
처리하는 것으로 이해했다.위의 로직 중에서, itemFactory.makeItem()이 그러한 구문이다.

#### 1-1. 일반적인 코드로부터 시작하기 (p69)
```java
public List<Item> filterSword(List<Item> items){
  List<Sword> list = new ArrayList();
  for(Item item : items){
    if(SWORD.equals(item.getType()){
      list.add(item);
    }
  }
  return result;
}
```
책에서는 녹색 사과를 통해서 예시를 들고 있으며, 예시가 정말 좋았지만, 공부를 하기 위해서
조금이나마 코드를 수정해보려고, item중에서 검을 분류하는 예제를 보여준다.
 일반적으로, 코드를 작성할 때, 다양한 변화를 생각하지 않고 작성할 때, 이와 같이 코드를
 작성할 것이다. 우리는 이 코드로부터 조금 더 다양하고, 다양한 변화에 적응하는 코드를
 작성하는 법을 알아보려고 한다.
 
#### 1-2. 비교하는 대상을 파라미터화 (p70)
  우리는 비교하는 대상인 SWORD를 비교함으로 인해서, 아이템들 중에서, 검을 필터화할 수 있었다.
  하지만, 검 외에도, 방패, 활, 창등을 검색하기 위해서는 어떻게 해야할까?
  1-1의 코드에서, SWORD를 비교하는 대신에, 메소드에 weaponType을 넘겨주면, 쉽게 무기의 타입을
  검사할 수 있다.
```java
public List<Item> filterItemsByType(List<Item> items, int weaponType){
  List<Sword> list = new ArrayList();
  for(Item item : items){
    if(weaponType.equals(item.getType()){
      list.add(item);
    }
  }
  return result;
}
```
 만약에, 무기의 타입이 아닌, 무기의 희귀도를 필터하려고 하면 어떻게 해야할까?
  ```java
public List<Item> filterItemsByUnique(List<Item> items, int unique){
  List<Sword> list = new ArrayList();
  for(Item item : items){
    if(item.getUnique() > unique){
      list.add(item);
    }
  }
  return result;
}
```
 그렇게 되면, for문 안의 if구문만 변경되게 된다.그러면 전체적인 구조는 똑같지만, 비교문만 바뀐채 비슷한 의미없는 메소드가 추가된다. 
 
#### 1-3. 비교하는 대상의 파라미터를 모두 합쳐보기(p71)
```java
public List<Item> filterItems(List<Item> items, int weaponType, int unique){
  List<Sword> list = new ArrayList();
  for(Item item : items){
    if(item.getUnique() > unique || item.getType().equals(weaponType)){
      list.add(item);
    }
  }
  return result;
}
```
 파라미터에는 items, weaponType, unique가 들어간다. 사용되어지는 파라미터를 모두 메소드를 통해서 관리하는 형태이다.
이리에 둘러 싸인 형국인 마냥, 이와 같은 방법은 하면 안된다고 설명되어져 있다.
``` java
List<Item> rareSwords = filterItems(items, SWORD, RARE);
List<Item> uniqueBows = filterItems(items, BOW, UNIQUE);
```
 이와 같은 코드가 작성된다. 사실 어느정도 깔끔해 보이지만, 어떤 기준으로 잘 정의되지 않는 상황이라면, 해당 코드는 위험하다고 한다.
 
### 2. 동작 파라미터화 (p72)
 - 1-3보다 더 유연한 코드가 필요하다.
 - 참 또는 거짓을 반환하는 함수를 프레디케이트라고 하는데, 그럴 땐, 프레디케이트 인터페이스를 사용하면 된다.
 
 ``` java
// 부모 인터페이스
public interface ItemPredicate{
  boolean logic(Item item);
}
```

#### 2-1. 전략 디자인 패턴을 활용해서 코드 재활용(동작 파라미터화)
부모 인터페이스를 작성하고 난 후, 해당 인터페이스를 상속받는 클래스를 만들어주자. 이러한 것을 전략 디자인 패턴(StrategyPattern)이라고 한다.
``` java
// filter(RARE, SWORD)
public class RareSwordsPredicate implements ItemPredicate{
  @Override
  public boolean logic(Item item){
    return item.getUnique() == RARE && item.getTtpe().equals(SWORD);
  }
}
```
 ``` java
// filter(RARE, SWORD)
public class UniqueBowsPredicate implements ItemPredicate{
  @Override
  public boolean logic(Item item){
    return item.getUnique() == UNIQUE && item.getTtpe().equals(BOW);
  }
}
```
``` java
public List<Item> filterItems(List<Item> items, ItemPredicate itemPredicate){
  List<Item> result = new ArrayList();
  for(Item item : items){
    if(itemPredicate.logic(item)){
      result.add(item);
    }
  }
  return result;
}
```
스트레지패턴을 활용해서 여러가지 조건에 맞게 필터링 클래스를 만든다. 그러게 되면 넘겨주는 predicate에 따라서, 결과값이 달라지도록 할 수 있다.
이를 이용해서, 동작을 파라미터화 했다고 할 수 있다.
``` java
List<Item> rareSwords = filterItems(items, new RareSwordPredicate());
List<Item> uniqueBows = filterItems(items, new UniqueBowsPredicate());
```
 나는 기존에 1-2나, 1-3의 방식으로 코드를 많이 작성하곤 하는데, 다양한 형태로 작업을 처리할게 있다면, 스트레지패턴을 활용해서
 동작을 파라미터화하면 좋겠다는 생각이 든다.
#### 2-2. 익명 클래스를 활용해서 동작 파라미터화 코드 작성
 익명 클래스는 이미 만들어놓은 인터페이스를 별다른 클래스를 구현하지 않고, 바로 사용하는 것을 의미한다. 안드로이드에서는 
setOnClickListener()에서 사용하곤 한다.
``` java
private void test(){
  button.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view){
      clickLogic(view);
    }
  }
}
```
 이와 같이 View.OnClickListener라는 인터페이스를 바로 구현한다. 이를 사용한 것처럼, Predicate를 클래스로 따로 구현하지 않고,
아래와 같이 편하게 쓸 수 있다.
 ``` java
List<Item> uniqueBows = filterItems(items, new ItemPredicate(){
  @Override
  public boolean logic(Item item){
    return item.getUnique() == UNIQUE && item.getTtpe().equals(BOW);
  }
});
```

#### 2-3. 람다 표현식을 활용해서 동작 파라미터화 코드 작성
 안드로이드 스튜디오에서는 setOnClickListener를 2-2의 예제와 같이 구현하게 되면, 람다식으로 변경해서 사용하라고 제안이 들어온다.
우리는 은연중에, 람다 표현식을 많이 사용했었다.
```java
private void test(){
  button.setOnClickListener((View view) -> clickLogic(view));
}
```
 위와 같이 람다식을 활용해서, 동작 파라미터화 코드를 더 줄일 수 있다.
```java
List<Item> uniqueBows = filterItems(items, (Item item)-> item.getUnique() == UNIQUE && item.getTtpe().equals(BOW));
```
