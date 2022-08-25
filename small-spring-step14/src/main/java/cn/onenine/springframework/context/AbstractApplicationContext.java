package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.context.event.ApplicationEventMulticaster;
import cn.onenine.springframework.context.event.ContextClosedEvent;
import cn.onenine.springframework.context.event.ContextRefreshedEvent;
import cn.onenine.springframework.context.event.SimpleApplicationEventMulticaster;
import cn.onenine.springframework.context.support.ApplicationContextAwareProcessor;
import cn.onenine.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
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

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationOnEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

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

        //3.添加ApplicationContextAwareProcessor，让实现自ApplicationContextAware的Bean对象都能感知到所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.BeanPostProcessor 需要提前与其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        //7.初始化事件发布者
        initApplicationEventMulticaster();

        //8.注册事件监听器
        registerListeners();

        //9.发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        //将事件广播器Bean注册到单例Bean容器中
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners() {
        //获取到所有配置的监听器Bean对象
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationLister(applicationListener);
        }
    }



    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
    public <T> T getBean(Class<?> fieldType) {
        return getBeanFactory().getBean(fieldType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布关闭上下文事件
        publishEvent(new ContextClosedEvent(this));
        //执行销毁方法
        getBeanFactory().destroySingletons();
    }
}
