package cn.onenine.springframework.beans.factory;


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
     * @param name Bean实例名称
     */
    Object getBean(String name);

    /**
     * 获取Bean
     * @param name Bean实例名称
     * @param args 用来传入创建实例对象所需的构造参数
     */
    Object getBean(String name , Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;




}
