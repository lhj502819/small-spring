# 增加功能 
## 实现 @Autowired依赖自动注入 和 @Value("${xx}")注入
### 需要的角色
- 解析Bean属性占位符，需要支持配置文件的占位符和@Value两种方式，`PropertyPlaceholderConfigurer`
- 
## 实现
- 属性占位符解析时机：在BeanDefinition注册完成后，完成对配置文件中的占位符配置进行解析，在创建完Bean实例之后，填充Bean属性之前，可以通过BeanPostProcessor修改属性值
  - `InstantiationAwareBeanPostProcessor`增加 在Bean创建之后，属性初始化之前执行的方法 `postProcessPropertyValues`，用于方便在初始化属性之前进行属性的调整，包括@Value注解的解析和@Autowired依赖注入


