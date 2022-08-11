# 增加功能
- 将Bean的定义、注册和初始化交给xml配置化处理

通过资源解析器`ResourceLoader`读取classpath、本地文件和云文件的配置内容。

# 缺点
- `DefaultListableBeanFactory`、`XmlBeanDefinitionReader`是在目前Spring框架中对于服务功能测试的使用方式，它能很好的体现出Spring是如何对xml加载以及注册Bean对象的操作过程，但这种方式是面向Spring本身的，还不具备一定的扩展性
- 比如现在需要提供一个可以在Bean初始化过程中，完成对Bean对象的扩展时，就很难做到自动化处理。因此要把Bean对象扩展机制功能和对Spring框架上下文的包装融合起来，对外提供完整的服务。
