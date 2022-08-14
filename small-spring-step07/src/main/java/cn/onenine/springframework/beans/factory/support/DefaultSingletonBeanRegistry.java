package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.DisposableBean;
import cn.onenine.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description：默认实现
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例Bean
     * 此方法可以被继承此类的其他类调用，包括{@link  AbstractBeanFactory }以及继承的 {@link  DefaultListableBeanFactory}
     *
     * @param beanName        bean名称
     * @param singletonObject bean实例对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
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
