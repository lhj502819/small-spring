# 增加功能 
## 实现通过扫描包路径自动注册Bean
### 需要的角色
- 扫描包路径，得到使用了`Component`注解的Class，<`ClassPathScanningCandidateComponentProvider`>
- 生成对应的BeanDefinition，通过注解`Scope`得到Bean的作用域，并将BeaDefinition注册到容器中，<`ClassPathBeanDefinitionScanner`>
- 读取xml中配置的需要扫描的包路径，对`XmlBeanDefinitionReader`的能力进行扩展，增加扫描`context:component-scan`，并结合上边两种能力，实现扫描包路径并自动注册Bean

## 实现占位符解析
- 通过实现`BeanFactoryPostProcessor`，在BeanDefinition初始化完成之后对字段为`${x}`占位符的字段进行解析装载，<`PropertyPlaceholderConfigurer`>
