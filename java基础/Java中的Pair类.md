# Java中的Pair类

C++中有pair<>类，存储键值对非常好用，在`javafx.util`中已经提供了Java的Pair类

## 应用场景

比如说要对map中的键值对，按value排序，就可以先将这些键值对存在`ArraList<Pair<Integer, String>>`中，用sort方法排序

```java
class Example{
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        List<Pair<Integer, String>> list = new ArrayList<>();
        map.put("abc", 2);
        list.add(new Pair<Integer, String>(2, "abc"));
        map.put("acd", 1);
        list.add(new Pair<Integer, String>(1, "acd"));
        map.put("bcd", 0);
        list.add(new Pair<Integer, String>(0, "bcd"));
        
        Comparator<Pair<Interger, String>> pairComp = (p1, p2) -> 
            p1.getKey().compareTo(p2.getKey);
        list.sort(pairComp);
    }
}
```

