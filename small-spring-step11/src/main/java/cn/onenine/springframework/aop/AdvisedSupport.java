package cn.onenine.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Description：包装类，包装切面通知信息
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:33
 */
public class AdvisedSupport {

    //false/true，JDK动态代理、CGlib动态代理
    private boolean proxyTargetClass = false;

    // 被代理的目标对象，在目标对象类中提供Object入参属性，以及获取目标类TargetClass信息
    private TargetSource targetSource;
    // 方法拦截器，具体拦截方法实现类，由用户自己实现
    private MethodInterceptor methodInterceptor;
    // 方法匹配器(检查目标方法是否符合通知条件)，由AspectJExpressionPointcut提供支撑服务
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    /**
     * 动态代理模式，false：JDK  true：Cglib
     * @return
     */
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
}
