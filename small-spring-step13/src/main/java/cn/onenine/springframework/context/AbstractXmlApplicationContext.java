package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.onenine.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Description：上下文中对配置信息的加载
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 22:37
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 为了从入口上下文类拿到配置信息的地址描述
     */
    protected abstract  String[] getConfigLocations();
}
