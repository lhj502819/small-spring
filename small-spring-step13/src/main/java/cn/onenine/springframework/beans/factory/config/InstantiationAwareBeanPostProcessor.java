package cn.onenine.springframework.beans.factory.config;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValues;

/**
 * Description：实现了BeanPostProcessor，增加了Bean对象初始化/填充属性之前的回调
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 21:17
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在Bean对象初始化之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在Bean创建之后，属性初始化之前执行
     */
    PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName);
}

