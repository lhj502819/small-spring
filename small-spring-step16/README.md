# 增加功能 
## 增加类型转换机制

## 需要的角色
- 转换器 Converter
- 转换器注册器 ConverterRegistry
- 转换器工厂 ConverterFactory
- 转换器API ConverterService

## 与Spring整合

在进行属性填充和@Value注解解析时整合，`AbstractAutowireCapableBeanFactory#applyPropertyValues`
和`AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues`