package cn.onenine.springframework;

import cn.hutool.core.io.IoUtil;
import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.PropertyValue;
import cn.onenine.springframework.beans.factory.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanReference;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.onenine.springframework.beans.factory.support.XmlBeanDefinitionReader;
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

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:important.properties");

        InputStream inputStream = resource.getInputStream();

        String content = IoUtil.readUtf8(inputStream);
        System.out.printf(content);
    }

    @Test
    public void test_file() throws Exception {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream in = resource.getInputStream();
        String content = IoUtil.readUtf8(in);
        System.out.printf(content);
    }


    /**
     * TODO 待测试为何拉取到流是整个html元素
     *
     * @throws Exception
     */
    @Test
    public void test_url() throws Exception {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() throws Exception {
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //3.获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();


    }


}
