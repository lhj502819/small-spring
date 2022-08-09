package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.core.io.Resource;
import cn.onenine.springframework.core.io.ResourceLoader;

import java.util.List;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:44
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    BeanDefinition loadBeanDefinition(Resource resource) throws Exception;

    List<BeanDefinition> loadBeanDefinitions(Resource resource) throws Exception;

    List<BeanDefinition> loadBeanDefinitions(String location) throws Exception;

}
