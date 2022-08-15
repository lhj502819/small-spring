# 增加功能
- 增加获取Spring容器资源的接口，如常用的BeanFactory、ApplicationContext

# 实现
- 增加Aware标记接口，同时增加多个资源的`Aware`接口，继承`Aware`接口
- 在创建Bean执行初始化方法时，增加对多个`Aware`的调用，除`ApplicationContext`外，因为创建Bean是在`BeanFactory`中执行的，无法获取`ApplicationContext`
- 因此增加`ApplicationContextAwareProcessor`，以在上下文刷新时将`ApplicationContextAware`以`BeanPostProcessor`的方式插入到`BeanPostProcessors`中，当创建Bean时则会执行`ApplicationContextAware`

# Spring Bean的生命周期
![img.png](img.png)