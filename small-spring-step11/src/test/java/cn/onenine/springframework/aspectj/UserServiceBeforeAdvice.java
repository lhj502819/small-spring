package cn.onenine.springframework.aspectj;

import cn.onenine.springframework.aop.MethodAfterAdvice;
import cn.onenine.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 22:05
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice, MethodAfterAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法 before " + method.getName() + "..........");
    }

    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法 after " + method.getName() + "............");
    }
}
