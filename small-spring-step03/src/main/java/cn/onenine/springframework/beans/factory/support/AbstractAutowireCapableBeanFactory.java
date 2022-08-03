package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        System.out.println("创建Bean实例");
        Object bean = null;
        try {
            //创建对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed" , e);
        }
        //添加到单例对象缓存中
        addSingleton(beanName,bean);
        return bean;
    }
}
