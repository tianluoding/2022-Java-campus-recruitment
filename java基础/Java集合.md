## Map的key需要满足的条件

1. 重写equals和hashCode方法
2. key类最好是不可变的，这样key对应的hashCode值可以被缓存起来，性能更好

## ConcurrentHashMap和HashTable的区别

锁粒度的区别：一个采用分段锁，一个Synchronized锁住整个数组

## 深拷贝和浅拷贝

Java里面都是值传递，如果要进行深拷贝需实现Cloneable接口



## Collection

#### 集合有哪些类？

- Set
  - TreeSet 基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如 HashSet，HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。
  - HashSet 基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
  - LinkedHashSet 具有 HashSet 的查找效率，且内部使用双向链表**维护元素的插入顺序**。
- List
  - ArrayList 基于动态数组实现，支持随机访问。
  - Vector 和 ArrayList 类似，但它是线程安全的。
  - LinkedList 基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。
- Queue
  - LinkedList 可以用它来实现双向队列。
  - PriorityQueue 基于堆结构实现，可以用它来实现优先队列



#### ArrayList的底层？

*ArrayList*实现了*List*接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入`null`元素，底层通过**数组实现**。Java泛型只是编译器提供的语法糖，所以这里的数组是一个Object数组，以便能够容纳任何类型的对象。默认容量为10

#### ArrayList自动扩容？

每当向数组中添加元素时，都要去检查添加后元素的个数是否会超出当前数组的长度，如果超出，数组将会进行扩容，以满足添加数据的需求。数组扩容通过**ensureCapacity(int minCapacity)**方法来实现。在实际添加大量元素前，我也可以使用ensureCapacity来手动增加ArrayList实例的容量，以减少递增式再分配的数量。

数组进行扩容时，会将老数组中的元素重新拷贝一份到新的数组中，每次数组容量的增长大约是其原容量的1.5倍。

#### ArrayList的Fail-Fast机制？

ArrayList也采用了快速失败的机制，通过记录modCount参数来实现。在面对并发的修改时，**迭代器**很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险。

* **foreach和fori遍历的区别？**

* 在对ArrayList进行遍历的时候不能使用list的add和remove方法，只能使用Iterator中的remove方法
* foreach用迭代器遍历list，迭代器里有一个expectedModCount属性，他的初始值是modCount，即：expectedModCount存储了获得迭代器的那一时刻ArrayList的结构修改次数。当我们调用ArrayList里的add或者remove()方法的时候，只修改了ArrayList里的modCount属性，没有修改内部类中expectedModCount属性。
* 那么如何遍历的同时remove元素呢？答：用迭代器的remove方法

## Map

#### Map有哪些类？

- `TreeMap` 基于红黑树实现。
- `HashMap` 1.7基于哈希表实现，1.8基于数组+链表+红黑树。
- `HashTable` 和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高(1.7 ConcurrentHashMap 引入了分段锁, 1.8 引入了红黑树)。
- `LinkedHashMap` 使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用(LRU)顺序。

#### JDK7 HashMap如何实现？

哈希表有两种实现方式，一种开放地址方式(Open addressing)，另一种是冲突链表方式(Separate chaining with linked lists)。**Java7 \*HashMap\*采用的是冲突链表方式**。

#### JDK8 HashMap如何实现？

根据 Java7 HashMap 的介绍，我们知道，查找的时候，根据 hash 值我们能够快速定位到数组的具体下标，但是之后的话，需要顺着链表一个个比较下去才能找到我们需要的，时间复杂度取决于链表的长度，为 O(n)。

为了降低这部分的开销，在 Java8 中，当链表中的元素达到了 8 个时，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。

#### HashSet是如何实现的？

*HashSet*是对*HashMap*的简单包装，对*HashSet*的函数调用都会转换成合适的*HashMap*方法

著作权归https://pdai.tech所有。 链接：https://pdai.tech/md/interview/x-interview.html

#### 什么是WeakHashMap?

**有效引用** 并不包括**弱引用**。也就是说，**虽然弱引用可以用来访问对象，但进行垃圾回收时弱引用并不会被考虑在内，仅有弱引用指向的对象仍然会被GC回收**。

WeakHashMap 内部是通过弱引用来管理entry的，弱引用的特性对应到 WeakHashMap 上意味着什么呢？

*WeakHashMap* 里的`entry`可能会被GC自动删除，即使程序员没有调用`remove()`或者`clear()`方法。

**WeakHashMap的这个特点特别适用于需要缓存的场景**。在缓存场景下，由于内存是有限的，不能缓存所有对象；对象缓存命中可以提高系统效率，但缓存MISS也不会造成错误，因为可以通过计算重新得到。