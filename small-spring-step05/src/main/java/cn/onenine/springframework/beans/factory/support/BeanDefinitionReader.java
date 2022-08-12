package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.core.io.Resource;
import cn.onenine.springframework.core.io.ResourceLoader;

/**
 * Description：Bean定义读取接口 ，Simple interface for bean definition readers.
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:44
 */
public interface BeanDefinitionReader {

    /**
     * {@link #getRegistry} 和 {@link  #getResourceLoader()}都是用于提供给后面三个方法的工具，加载和注册。
     *  这两个方法的实现会包装到具体的抽象类中，以免污染具体的接口实现方法。
     */


    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws Exception;

    void loadBeanDefinitions(Resource... resource) throws Exception;

    void loadBeanDefinitions(String location) throws Exception;

}
