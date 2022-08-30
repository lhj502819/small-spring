# Small-Spring

从零手写简易版Spring框架。

1. [实现超级简单的依赖注入和依赖查找](https://github.com/lhj502819/small-spring/tree/main/small-spring-step01)
2. [实现Bean的定义、注册、和获取](https://github.com/lhj502819/small-spring/tree/main/small-spring-step02)
3. [增加不同的Bean实例化策略，JDK和Cglib两种方式](https://github.com/lhj502819/small-spring/blob/main/small-spring-step03)
4. [增加Bean实例化后对属性进行填充](https://github.com/lhj502819/small-spring/tree/main/small-spring-step04)
5. [增加Bean的定义相关配置化，将Bean的定义、注册和初始化交给xml配置化处理](https://github.com/lhj502819/small-spring/tree/main/small-spring-step05)
6. [实现IOC的的扩展功能，BeanFactoryPostProcessor、BeanPostProcessor](https://github.com/lhj502819/small-spring/tree/main/small-spring-step07)
7. [增加获取Spring容器资源(BeanFactory、ApplicationContext、BeanName)的接口，一系列的Aware](https://github.com/lhj502819/small-spring/tree/main/small-spring-step07)
8. [增加单例和原型两种Bean作用域的支持，增加增加FactoryBean接口](https://github.com/lhj502819/small-spring/tree/main/small-spring-step08)
9. [增加事件机制，在刷新上下文的过程中增加对对事件发布器的注册和事件监听器的Bean注册](https://github.com/lhj502819/small-spring/tree/main/small-spring-step09)
10. [实现简易AOP](https://github.com/lhj502819/small-spring/tree/main/small-spring-step10)
11. [将AOP与Spring进行整合](https://github.com/lhj502819/small-spring/tree/main/small-spring-step11)
12. [实现通过扫描包路径自动注册Bean以及属性值的占位符解析](https://github.com/lhj502819/small-spring/tree/main/small-spring-step12)
13. [实现 @Autowired依赖自动注入 和 @Value("${xx}")注入](https://github.com/lhj502819/small-spring/tree/main/small-spring-step13)
14. [实现对代理对象的属性注入](https://github.com/lhj502819/small-spring/tree/main/small-spring-step14)
15. [通过缓存解决的Bean的循环依赖问题](https://github.com/lhj502819/small-spring/tree/main/small-spring-step15)
16. [增加类型转换机制](https://github.com/lhj502819/small-spring/tree/main/small-spring-step16)






## Spring Bean的生命周期
整个SpringIOC容器的启动是在 `AbstractApplicationContext#refresh`中执行的，包含了整个BEAN的生命周期
 ![IMG.PNG](img.png)

## BeanFactory和ApplicationContext的区别
> 官方文档：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-beanfactory
- BeanFactory提供了基础SPRING IOC的功能的基础，主要用于在SPRING框架内部使用以及与其他第三方框架的集成
- ApplicationContext包含了BeanFactory支持的所有功能，ApplicationContext会自动检测几种BEAN，例如注解处理和AOP代理，如果使用BEANFACTORY则不会执行，
包括对于BeanPostProcessor的检测和注册，BeanFactory都不会自动执行


## SPRING IOC容器的扩展点
> 官方文档 https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension
- 自定义BEAN：`BeanPostProcessor`
- 自定义配置元数据：`BeanFactoryPostProcessor`
- 自定义BEAN的初始化逻辑：`FactoryBean`


### BeanPostProcessor
用于在BEAN实例化之后，执行初始化方法之前和执行初始化方法之后进行扩展；

初始化方法指的是我们配置的`init-method`或者`Initializingbean#afterPropertiesSet`

### BeanFactoryPostProcessor
用于在所有的BEAN实例化之前，修改配置元信息，比如进行配置文件的读取，进行占位符替换`${}`，如`Propertyplaceholderconfigurer`，

**注意事项**：在`BeanFactoryPostProcessor`中是不建议获取BEAN实例(调用#GETBEAN方法)，因为这样会导致BEAN的提前实例化,一方面违反了BEAN的生命周期约定，另一方面会导致BEANPOSTPROCESSOR不执行

### FactoryBean

用于应对复杂的对象创建，无法用JAVA程序表达，例如XML，可以在FACTORYBEAN中编写复杂的逻辑，然后将FACTORYBEAN交给SPRING IOC容器.

**注意事项：** 我们实现的FACTORYBEAN在配置时需要将BEAN名称注册为 '&'开头，见`BeanFactory#FACTORY_BEAN_PREFIX`，IOC容器在创建BEAN时会以BEANNAME是否以`&`为前缀来判断是否为`FACTORYBEAN`，
同时需要注意我们在使用SPRING时如果不是FACTORYBEAN的实现，也不要将BEAN实例名称设置为`&`开头，会抛出`BeanIsNotAFactoryException`


# 参考
- [手撸Spring-小傅哥](https://github.com/fuzhengwei/small-spring)
- [mini-spring](https://github.com/DerekYRC/mini-spring)
- [Spring官方文档](https://spring.io/projects/spring-framework/)
 
