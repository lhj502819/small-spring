package cn.onenine.springframework.aspectj;

import cn.onenine.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 22:05
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法 " + method.getName() + "..........");
    }
}
