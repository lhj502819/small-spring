package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * Description：应用上下文抽象类实现
 *      继承{@link DefaultResourceLoader}是为了处理spring.xml配置资源的加载
 *      把定义出来的抽象方法，{@link  #refreshBeanFactory()}和、{@link  #getBeanFactory()}由后面继承此抽象类的其他抽象类实现
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:52
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements  ConfigurableApplicationContext {

    /**
     * 资源准备，BeanFactory、PostProcessor、BeanDefinition
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        //1.创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4.BeanPostProcessor 需要提前与其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }



    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name,args);
    }
    @Override
    public <T> T getBean(String name, Class<T> type) throws BeansException {
        return getBeanFactory().getBean(name,type);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
