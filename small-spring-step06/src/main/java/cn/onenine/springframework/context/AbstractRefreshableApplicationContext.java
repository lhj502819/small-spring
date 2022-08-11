package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Description：获取Bean工厂和加载资源
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 22:16
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        //获取DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        //加载spring.xml配置文件中Bean对象的定义和注册，同时也包括实现了接口BeanFactoryPostProcessor、BeanPostProcessor的配置Bean信息
        //具体的加载方法还得由具体的实现类继承类实现
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
