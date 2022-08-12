package cn.onenine.springframework;

import cn.hutool.core.io.IoUtil;
import cn.onenine.springframework.bean.MyBeanFactoryPostProcessor;
import cn.onenine.springframework.bean.MyBeanPostProcessor;
import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.PropertyValue;
import cn.onenine.springframework.beans.factory.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanReference;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.onenine.springframework.beans.factory.support.XmlBeanDefinitionReader;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import cn.onenine.springframework.core.io.DefaultResourceLoader;
import cn.onenine.springframework.core.io.Resource;
import cn.onenine.springframework.dao.UserDao;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;


    /**
     * 不使用应用上下文
     */
    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor(){
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.读取配置文件 & 注册
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //3.BeanDefinition 加载完成 & 实例化之前，修改BeanDefinition的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        //4.Bean实例化之后，修改Bean属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        //5.获取Bean对象，调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }


    /**
     * 使用应用上下文
     */
    @Test
    public void testWithApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        applicationContext.refresh();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }


}
