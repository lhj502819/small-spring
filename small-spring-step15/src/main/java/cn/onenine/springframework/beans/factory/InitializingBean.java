package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * Description：初始化Bean后执行的操作
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 11:29
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用 
     * {@link AbstractAutowireCapableBeanFactory#applyPropertyValues}
     */
    void afterPropertiesSet() throws Exception;
}
