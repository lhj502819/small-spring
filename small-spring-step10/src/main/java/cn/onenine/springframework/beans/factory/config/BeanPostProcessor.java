package cn.onenine.springframework.beans.factory.config;


import cn.onenine.springframework.beans.BeansException;

/**
 * Description：提供了修改新实例化Bean对象的扩展点
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 20:49
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
