# 增加功能
- 增加单例和原型两种Bean的支持
  - 单例和原型模式的区别：是否放到内存中，如果是原型模式就不会把Bean放到内存中，每次获取都会重新创建一个新的实例，这种Bean从使用意义上来讲是不需要执行销毁方法的；Singleton Bean则创建完之后则会放到内存中循环使用
  - 对应到程序实现中，也就是在创建Bean向内存中添加Bean的时候判断该Bean是否为Singleton类型，同时在注册Bean的DisposableBean时也需要进行判断，对应的实现类为`AbstractAutowireCapableBeanFactory`
# 实现
- 增加Aware标记接口，同时增加多个资源的`Aware`接口，继承`Aware`接口
- 在创建Bean执行初始化方法时，增加对多个`Aware`的调用，除`ApplicationContext`外，因为创建Bean是在`BeanFactory`中执行的，无法获取`ApplicationContext`
- 因此增加`ApplicationContextAwareProcessor`，以在上下文刷新时将`ApplicationContextAware`以`BeanPostProcessor`的方式插入到`BeanPostProcessors`中，当创建Bean时则会执行`ApplicationContextAware`

# Spring Bean的生命周期
![img.png](img.png)