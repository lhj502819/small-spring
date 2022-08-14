package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Description：可配置并的BeanFactory
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:12
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory,ConfigurableBeanFactory {
    void preInstantiateSingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


}
