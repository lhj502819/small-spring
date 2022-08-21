package cn.onenine.springframework.aop.framework;

import cn.onenine.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description：基于JDK动态代理实现AOP
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:39
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                advised.getTargetSource().getTargetClass(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //判断方法是否匹配表达式
        if (advised.getMethodMatcher()
                .matches(method, advised.getTargetSource().getTarget().getClass())) {
            //获取用户自定义的拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            //通过拦截器执行
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),
                    method, args));
        }
        //不匹配则正常执行方法
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
