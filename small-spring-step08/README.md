# 增加功能
- 增加单例和原型两种Bean的支持
  - 单例和原型模式的区别：是否放到内存中，如果是原型模式就不会把Bean放到内存中，每次获取都会重新创建一个新的实例，这种Bean从使用意义上来讲是不需要执行销毁方法的；Singleton Bean则创建完之后则会放到内存中循环使用
  - 对应到程序实现中，也就是在创建Bean向内存中添加Bean的时候判断该Bean是否为Singleton类型，同时在注册Bean的DisposableBean时也需要进行判断，对应的实现类为`AbstractAutowireCapableBeanFactory`
- 增加FactoryBean接口，作用如下：
  - 用来完成不需要实现接口仍然可以生成Bean实例，比如我们在使用MyBatis时，只需要定义一个接口，即可完成对数据库的操作，其在底层其实是创建了一个Bean实例，并交给了 Spring 容器管理，那是怎么交给Spring管理的呢？就是通过实现了FactoryBean接口。
  - 用于应对复杂的对象创建，无法用Java程序表达，例如xml，可以在FactoryBean中编写复杂的逻辑，然后将FactoryBean交给Spring IOC容器.
- Spring容器只管理FactoryBean的生命周期，不管 FactoryBean 创建实例的生命周期
- 支持单例和原型两种scope
