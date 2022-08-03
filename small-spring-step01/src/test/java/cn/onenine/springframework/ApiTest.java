package cn.onenine.springframework;

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
        BeanFactory beanFactory = new BeanFactory();

        //2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService" , beanDefinition);

        //3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo());
    }

}
