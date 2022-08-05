## 增加功能
实现Bean的定义、注册、和获取
- `BeanDefinitionRegistry`定义了注册BeanDefinition的行为
- `SingletonBeanRegistry`定义获取单例Bean的方法
- `DefaultSingletonBeanRegistry` 实现`SingletonBeanRegistry`获取单例bean接口，增加“添加单例Bean”方法
- `AbstractBeanFactory` 实现了`BeanFactory`接口，继承了`DefaultSingletonBeanRegistry`，因此具有了获取Bean和注册单例Bean的能力，主要是提供获取Bean的模板方法行为
- `AbstractAutowireCapableBeanFactory` 继承了`AbstractBeanFactory`，提供创建Bean实例的实现
- `DefaultListableBeanFactory` 继承了`AbstractAutowireCapableBeanFactory`，实现了`BeanDefinitionRegistry`的注册Bean定义接口，提供单例Bean的获取