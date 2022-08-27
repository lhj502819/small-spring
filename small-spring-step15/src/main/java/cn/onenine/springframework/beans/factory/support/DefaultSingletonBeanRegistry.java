package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.DisposableBean;
import cn.onenine.springframework.beans.factory.ObjectFactory;
import cn.onenine.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description：默认实现
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 一级缓存 用于存放成品对象
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 二级缓存 用于存放半成品对象
     */
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    /**
     * 三级缓存 用于存放代理对象
     */
    private Map<String, ObjectFactory> singletonFactories = new HashMap<>();


    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    protected Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null) {
            //从二级缓存中获取，如果二级缓存中没有，则说明这个对象一定是代理对象，因为只有代理对象才在三级缓存中
            singletonObject = earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if (objectFactory != null) {
                    singletonObject = objectFactory.getObject();
                    //把三级缓存中的对象取出来放到二级缓存中
                    earlySingletonObjects.put(beanName,singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    /**
     * 添加单例Bean
     * 此方法可以被继承此类的其他类调用，包括{@link  AbstractBeanFactory }以及继承的 {@link  DefaultListableBeanFactory}
     *
     * @param beanName        bean名称
     * @param singletonObject bean实例对象
     */
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    /**
     * 添加三级缓存
     */
    protected void addSingletFactory(String beanName,ObjectFactory<?> singletonFactory){
        //如果一级缓存中没有的才添加
        if(!singletonObjects.containsKey(beanName)){
            singletonFactories.put(beanName,singletonFactory);
            earlySingletonObjects.remove(beanName);
        }
    }


    /**
     * 添加DisposableBean，方便在Bean销毁时执行销毁逻辑
     */
    protected void registryDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        //从disposableBeanMap移除Bean
        Set<String> beanNames = disposableBeanMap.keySet();
        Object[] beanNamesArray = beanNames.toArray();
        for (int i = beanNamesArray.length - 1; i >= 0; i--) {
            Object beanName = beanNamesArray[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("执行Destroy方法异常，beanName：" + beanName);
            }
        }
    }

}
