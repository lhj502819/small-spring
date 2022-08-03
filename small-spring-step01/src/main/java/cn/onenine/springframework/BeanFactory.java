package cn.onenine.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description：Bean工厂，用来操作Bean，如注册、获取
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 获取Bean
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 注册Bean
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName ,  BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName , beanDefinition);
    }
}
