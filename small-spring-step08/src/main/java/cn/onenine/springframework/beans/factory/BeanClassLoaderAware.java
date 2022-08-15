package cn.onenine.springframework.beans.factory;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 20:29
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}
