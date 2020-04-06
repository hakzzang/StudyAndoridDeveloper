### 공부하는 것은 내 맴
- ModernJavaInAction 책을 읽고, 정리하고자 작성하는 md 파일임을 말하고,
  꼭 해당 책을 읽어봤으면, 하는 생각을 갖고 있습니다. 혹시, 문제가 되는 사항이 있다면,
  알려주시면 감사하겠습니다.
  
1. 동적 파라미터화 코드(Behavior Parameterization)
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

1-1. 일반적인 코드로부터 시작하기
```java
public List<Item> filterSword(List<Item> items){
  List<Sword> list = new ArrayList();
  for(Item item : items){
    if(SWORD.equals(item.getKind()){
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
