package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.beans.factory.config.SingletonBeanRegistry;
import cn.onenine.springframework.utils.StringValueResolver;

/**
 * Description：可配置的BeanFactory
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 22:07
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 用于解决@Value注解标注的字段
     */
    String resolveEmbeddedValue(String value);
}
