package cn.onenine.springframework.aop.framework.adapter;

import cn.onenine.springframework.aop.MethodAfterAdvice;
import cn.onenine.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Description：方法前置拦截器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 21:00
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor() {
    }

    public MethodAfterAdviceInterceptor(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //用于自己实现MethodBeforeAdvice接口后做的相应处理
        this.advice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());

        return invocation.proceed();
    }


}
