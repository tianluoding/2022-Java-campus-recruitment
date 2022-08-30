# 原子类

1. 基本类型
   * AtomicInteger
   * AtomicLong
   * AtomicBoolean
2. 数组类型
   * AtomicIntegerArray
   * AtomicLongArray
   * AtomicReferenceArray
3. 引用类型
   * AtomicReference
   * AtomicStampedReference： 原子更新带版本号的引用类型 用于解决ABA问题
   * AtomicMarkableReference：原子更新带有标记位的引用类型 
4. 对象属性修改类型
   * AtomicIntegerFieldUpdater
   * AtomicLongFieldUpdater

## AtomicInteger

CAS+volatile保证原子操作

