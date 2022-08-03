package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description：Bean工厂，用来操作Bean，如注册、获取
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public interface BeanFactory {

    /**
     * 获取Bean
     * @param name
     * @return
     */
    Object getBean(String name);




}
