package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.onenine.springframework.beans.PropertyValue;
import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Description：自定义BeanFactoryPostProcessor
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/12 22:30
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company" , "改为：字节跳动"));

    }
}
