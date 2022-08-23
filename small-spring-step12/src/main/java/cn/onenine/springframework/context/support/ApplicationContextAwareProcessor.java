package cn.onenine.springframework.context.support;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import cn.onenine.springframework.context.ApplicationContext;
import cn.onenine.springframework.context.ApplicationContextAware;

/**
 * Description：由于ApplicationContext的获取不能在创建Bean的时候拿到，因此需要在refresh的时候
 *  把ApplicationContext写入到一个包装的BeanPostProcessor中
 *  再由{@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}方法调用
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 20:36
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
