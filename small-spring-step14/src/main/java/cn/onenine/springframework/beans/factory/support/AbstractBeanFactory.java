package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.ConfigurableBeanFactory;
import cn.onenine.springframework.beans.factory.FactoryBean;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.utils.ClassUtils;
import cn.onenine.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板方法类
 * 继承{@link  DefaultSingletonBeanRegistry }具备了使用单例注册类方法
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 要应用的字符串解析器，例如注释属性值
     */
    private final List<StringValueResolver> embeddedValueResolver = new ArrayList<>();


    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }


    /**
     * 根据BeanDefinition创建Bean
     */
    private <T> T doGetBean(String name, Object[] args) {
        Object sharedInstance = getSingleton(name);

        if (sharedInstance != null) {
            //如果是FactoryBean，需要调用FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        //如果是FactoryBean，需要调用FactoryBean#getObject
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        //判断缓存中是否有该Bean实例
        Object object = getCachedObjectFromFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        embeddedValueResolver.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : embeddedValueResolver) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
