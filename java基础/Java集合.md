## Map的key需要满足的条件

1. 重写equals和hashCode方法
2. key类最好是不可变的，这样key对应的hashCode值可以被缓存起来，性能更好

## ConcurrentHashMap和HashTable的区别

锁粒度的区别：一个采用分段锁，一个Synchronized锁住整个数组

## 深拷贝和浅拷贝

Java里面都是值传递，如果要进行深拷贝需实现Cloneable接口