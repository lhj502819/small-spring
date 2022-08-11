# 增加功能
- 增加在实例化后进行属性填充
# 缺点
- 使用时都需要手动去硬编码注册Bean，繁琐，实用性较低
- ```
  //3.UserService设置属性[uId，userDao]
  PropertyValues propertyValues = new PropertyValues();
  propertyValues.addPropertyValue(new PropertyValue("uId" , "1002"));
  propertyValues.addPropertyValue(new PropertyValue("userDao" , new BeanReference("userDao")));

  //4.UserService注入Bean
  BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
  beanFactory.registerBeanDefinition("userService" , beanDefinition);```
