## 方法区（元空间）中存在什么？

1. 被加载的类信息
2. 常量
3. 静态变量
4. 即时编译器编译后的代码
5. 。。。

## ThreadLocal内存泄漏问题

ThreadLocal变量是存在ThreadLocalMap中，其中key为弱引用，value是强引用，当ThreadLocal没有被强引用时，key会被垃圾回收，value不会

* 强引用
* 软引用
* 弱引用
* 虚引用

## ThreadPoolExecutor构造函数参数

1. corePoolSize
2. maximumPoolSize
3. workQueue
4. keepAliveTime
5. unit
6. threadFactory
7. handler