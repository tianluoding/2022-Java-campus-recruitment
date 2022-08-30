# Java自定义排序

Comparator和Comparable的区别

1. 让类实现Comparable接口，使得类天生具有比较能力
2. 实现Comparator接口，实现其中的compare方法

这里重点说一下Comparator接口，配合Pair使用

* 使用 Lambda 表达式定义 compare
  我们使用lambda表达式定义compare功能方法如下。

  `Comparator<Student> ageComp = (s1, s2) -> s1.getAge() - s2.getAge();`
  `Comparator<Student> nameComp = (s1, s2) -> s1.getName().compareTo(s2.getName()); `

  

