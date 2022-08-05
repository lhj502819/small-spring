package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：默认实现
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例Bean
     * 此方法可以被继承此类的其他类调用，包括{@link  AbstractBeanFactory }以及继承的 {@link  DefaultListableBeanFactory}
     * @param beanName bean名称
     * @param singletonObject bean实例对象
     */
    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
}
