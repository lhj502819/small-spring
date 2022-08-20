package cn.onenine.springframework.aop;

import java.lang.reflect.Method;

/**
 * Description：方法匹配器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:08
 */
public interface MethodMatcher {

    /**
     * 判断方法是否匹配规则
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
