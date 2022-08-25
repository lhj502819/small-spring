# 增加功能 
## 实现对代理对象的属性注入
- 原有的代理对象只是在对象实例化之前进行了拦截，进行了代理的对象的创建，但缺少了对属性的填充，也就是不在Bean的生命周期中
- 因此需要在Bean的初始化方法执行完成之后再创建代理对象，将这个步骤移到Bean的生命周期中，之前的`DefaultAdvisorAutoProxyCreator`实现了`BeanPostProcessor#postProcessBeforeInstantiation`，因此也需要把这个逻辑移动到`#DefaultAdvisorAutoProxyCreator`中
- 同时在创建代理对象是，targetObject则是已经创建完成的Bean实例，这个Bean实例的属性也已经填充/注入完毕了
