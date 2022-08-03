package cn.onenine.springframework;

import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){

        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        //3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo());
        //4.第二次获取bean，从 singletonMap中获取
        UserService userService_singleton = (UserService)beanFactory.getBean("userService");
        System.out.println(userService_singleton.queryUserInfo());


    }

}
