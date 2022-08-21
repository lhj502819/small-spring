package cn.onenine.springframework.aop.framework.autoproxy;

import cn.onenine.springframework.aop.*;
import cn.onenine.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.onenine.springframework.aop.framework.ProxyFactory;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.BeanFactoryAware;
import cn.onenine.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * Description：主要核心实现在于postProcessBeforeInstantiation方法中
 *  获取了所有的advisors以后就可以遍历相应的AspectJExpressionPointcutAdvisor，填充对应的属性信息，
 *      包括目标对象、拦截方法、匹配器，之后返回代理对象即可
 *      此时现在调用方法获取到的这个Bean对象就是一个被切面注入的代理对象了，当调用方法的时候，则会被按需拦截处理
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 21:15
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 返回目标Bean的代理对象
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(isInfrastructureClass(beanClass)){
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(beanClass)){
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }
}
