# 增加功能
- 增加按照是否有构造函数实现不同的实例化策略
- 增加实例化Bean策略类 `InstantiationStrategy`
  - JDK原生 `SimpleInstantiationStrategy`
  - Cglib方式 `CglibInstantiationStrategy`
# 缺点
- 缺少对实例字段的填充，不只是基本数据类型，还包括没有实例化的对象属性，都需要在Bean创建的时候进行填充