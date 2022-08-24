package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.core.io.DefaultResourceLoader;
import cn.onenine.springframework.core.io.ResourceLoader;


/**
 * Description：把BeanDefinitionReader接口的前两个方法全部实现完了，并提供构造函数
 *  让外部的调用使用方，把Bean定义注入类，传递进来，
 *  这样在接口BeanDefinitionReader的具体实现类中，就可以把解析后的XML文件中的Bean信息，注册到Spring容器中去了
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:46
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{


    private final BeanDefinitionRegistry registry;

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
