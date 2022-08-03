package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.config.BeanDefinition;

/**
 * Description：Bean定义注册
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册BeanDefinition
     * @param beanName 名称
     * @param beanDefinition Bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
