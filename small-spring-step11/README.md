# 增加功能 - 将AOP与Spring进行整合
- 封装原有的API，如组装代理信息（目标代理对象、切面、切面表达式）以及创建代理的过程
- 改为由用户配置完成，将生成代理对象与Bean的生命周期整合，并提供BeforeAdvice、AfterAdvice等通知模式
## 需要的角色
### 功能支撑
- `Advice`适配器`MethodBeforeAdviceInterceptor`，将Advice适配为MethodInterceptor，因为拦截器的执行都是通过`MethodInterceptor`去执行的
- `AOP`角色包装类`AspectJExpressionPointcutAdvisor`，用于包装切面表达式、切面(Advice)、切点，主要用于开放给用户使用，在xml中配置


