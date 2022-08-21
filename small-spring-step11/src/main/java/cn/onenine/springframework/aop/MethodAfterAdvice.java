package cn.onenine.springframework.aop;

import java.lang.reflect.Method;

/**
 * Description：方法后置通知
 *   Spring中Advice都是通过方法拦截器MethodInterceptor实现的
 *      环绕的Advice类似一个拦截器的链路，Before Advice、After Advice
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 20:41
 */
public interface MethodAfterAdvice extends BeforeAdvice{

    void after(Method method, Object[] args,Object target) throws Throwable;

}
