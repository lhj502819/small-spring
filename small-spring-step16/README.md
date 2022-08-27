# 增加功能 
## 解决的Bean的循环依赖问题
## 循环依赖的场景
- 自身依赖于自身
- 互相循环依赖
- 多组循环依赖（间接）

## 使用缓存解决
- 分为三个缓存：成品对象、半成品对象（未填充属性）、代理对象
- 在Bean对象创建完之后还没有填充属性之前先放到缓存中，主要实现为 `AbstractAutowireCapableBeanFactory#createBean`和`AbstractBeanFactory#doGetBean`

