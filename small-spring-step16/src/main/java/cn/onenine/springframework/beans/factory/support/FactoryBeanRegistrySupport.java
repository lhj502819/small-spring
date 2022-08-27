package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description： 用来提供FactoryBean的注册
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/15 23:09
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectFromFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean,beanName);
                this.factoryBeanObjectCache.put(beanName, object != null ? object : NULL_OBJECT);
            }
            return object != NULL_OBJECT ? object : null;
        }else {
            return doGetObjectFromFactoryBean(factoryBean,beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean,String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("Factory threw exception on object [" + beanName + "] creation " , e);
        }
    }


}
