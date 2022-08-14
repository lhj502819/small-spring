package cn.onenine.springframework.beans.factory;

import java.util.Map;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:14
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回Bean实例
     * @param type Bean类型
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
