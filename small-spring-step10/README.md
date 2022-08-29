# 增加功能 - 实现简易AOP
## 基础知识点
### 切面表达式
- JoinPoint：织入点，指需要执行代理操作的某个类的某个方法（仅支持方法级别的JoinPoint）
- PointCut：是JoinPoint的表述形式，能捕获JoinPoint，简称切点表达式

最常见的切面表达式是AspectJ的表达式，需要匹配类，定义ClassFilter，定义MethodMatcher接口，PointCut需要同时匹配类和方法
### 几种Advice模式
- Before
- After
- AfterReturningAdvice
- ThrowsAdvice
## 需要的角色
### 功能支撑
- 方法匹配器（`MethodMatcher`）- 判断方法是否满足切点表达式
- 类过滤器（`ClassFilter`）- 判断Class是否满足切点表达式
- 切点表达式类（`AspectJExpressionPointcut`） - 聚合实现方法匹配器、类过滤器，是支持切点表达式的PointCut实现
- 门面类（包装类）`AdvisedSupport` - 组合方法匹配器(`MethodMatcher`)、被代理的目标对象(`targetObject`)、用户自定义的拦截器(`MethodInterceptor`)
- 目标代理对象包装类（`TargetSource`），包含被代理对象，并提供基础数据获取，Class、Interfaces
- 对AOP联盟`MethodInvocation`的实现：`ReflectiveMethodInvocation`，方法入参包装类，包含：被代理对象、代理的方法、方法参数
- Advice的实现，即`MethodInterceptor`
- PointcutAdvisor，Pointcut和Advice的结合，Pointcut用于捕获JoinPoint，Advice决定在JoinPoint执行某种操作
## 设计
- 实现对象代理，首先需要有获取目标对象的代理对象的方法，其次因实现代理的具体方式不同，因此通过抽象接口，多种实现的方式完成，本示例中提供的方式为Jdk和Cglib两种方式
- 具体的代理实现中，需要明确自己需要代理的目标对象、代理需要拦截方法执行的拦截器（Advice切面->Interceptor拦截器），而具体的Interceptor的执行则需要(Joinpoint 切点->Invocation执行器)，那么AdvisedSupport则是将这些进行了组装
- 通过BeanPostProcessor融入到Bean的生命周期中，在Bean实例化之前，生成代理对象，这里其实是有问题的，生成代理对象之后还需要进行属性的填充/注入，在[step14](https://github.com/lhj502819/small-spring/tree/main/small-spring-step14)中我们会进行完善调整。
### 对外API
- 获取代理对象 (`AopProxy`)
  - 具体获取代理对象的实现：Jdk、Cglib
- 拦截器：实现AOP联盟的`MethodInterceptor`，`MethodInterceptor`需要与`MethodInvocation`配合使用

# 类图
![img_2.png](img_2.png)

# 缺点
- 没有与Spring进行整合，这样直接给用户使用很不友好

