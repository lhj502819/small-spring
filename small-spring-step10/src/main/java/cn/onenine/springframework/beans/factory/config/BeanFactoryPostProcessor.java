package cn.onenine.springframework.beans.factory.config;

import cn.onenine.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * Description：用于在所有的BeanDefinition加载后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 20:45
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成之后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
