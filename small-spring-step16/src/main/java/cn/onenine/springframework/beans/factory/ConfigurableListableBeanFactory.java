package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * Description：可配置并的BeanFactory
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:12
 */
public interface ConfigurableListableBeanFactory extends  ListableBeanFactory,ConfigurableBeanFactory, AutowireCapableBeanFactory {
    void preInstantiateSingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


}
