# 增加功能 - 实现事件机制
- 事件：ApplicationEvent
- 监听类：ApplicationListener
- 事件发布类：ApplicationEventMulticaster

# 实现
- 在刷新上下文的过程中增加对对事件发布器的注册和事件监听器的Bean注册(`AbstractApplicationContext#refresh`)

# 总结-SpringIOC容器的启动过程

![img.png](img.png)
