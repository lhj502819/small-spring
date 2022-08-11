package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Description：Bean实例化策略接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/4
 */
public interface InstantiationStrategy {

    /**
     * 实例化Bean
     * @param beanDefinition Bean定义
     * @param beanName Bean名称
     * @param ctor 用来拿到符合入参信息相对应的构造函数
     * @param args 构造函数所需参数
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
