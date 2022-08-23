package cn.onenine.springframework;

import cn.onenine.springframework.test.bean.UserService;
import cn.onenine.springframework.beans.factory.PropertyValue;
import cn.onenine.springframework.beans.factory.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanReference;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.onenine.springframework.test.dao.UserDao;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.UserDao注册
        beanFactory.registerBeanDefinition("userDao" , new BeanDefinition(UserDao.class));

        //3.UserService设置属性[uId，userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId" , "1002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao" , new BeanReference("userDao")));

        //4.UserService注入Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService" , beanDefinition);

        //5.获取UserService Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


}
