package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.BeansException;

/**
 * Description：容器感知类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 20:29
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
