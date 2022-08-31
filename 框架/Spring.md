# Spring

## IOC和AOP

IoC控制反转，将创建对象的控制权，交给Spring框架管理，需要创建对象的时候，只需要注入就行。

`@Componet`注解将类自动装配到Spring容器中

AOP基于动态代理，减少系统的重复代码，降低模块间的耦合度，增加可扩展性和可维护性；实际上就是把一些通用功能抽象出来，在需要的地方直接使用。

## @Bean和@Component

@Bean通常用来注入第三方bean，写在方法之上

@Component写在类之上，也是将对象装配到Spring容器中

将一个类声明为Spring的bean的注解：

* @Component
* @Repository
* @Service
* @Controller

## Spring框架中的设计模式

* 工厂模式 创建bean对象
* 代理模式 AOP
* 单例模式 Spring容器中的Bean默认是单例
* 包装器模式
* 观察者模式：Spring事件驱动模型就是观察者模式
* 适配器模式