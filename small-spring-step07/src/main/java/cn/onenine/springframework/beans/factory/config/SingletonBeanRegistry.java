package cn.onenine.springframework.beans.factory.config;

/**
 * Description：单例Bean注册接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例Bean实例
     * @param beanName bean名称
     */
    Object getSingleton(String beanName);

}
