package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;

/**
 * 模板方法类
 * 继承{@link  DefaultSingletonBeanRegistry }具备了使用单例注册类方法
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 此过程中主要实现了当获取不到单例Bean的时候，需要做相应的Bean实例化操作，而这里并没有具体的实例化的实现，
     * 而是只定义了调用过程并提供了抽象方法，由实现类完成相应实现
     *
     * @param name bean名称
     */
    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    /**
     * 根据BeanDefinition创建Bean
     */
    private <T> T doGetBean(String name, Object[] args) {
        Object bean = getSingleton(name);

        if (bean != null) {
            System.out.println("从SingletonMap中获取Bean实例");
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);

        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
