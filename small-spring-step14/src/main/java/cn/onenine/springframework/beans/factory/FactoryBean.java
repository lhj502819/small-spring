package cn.onenine.springframework.beans.factory;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/15 23:07
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
