package cn.onenine.springframework.test;

import cn.onenine.springframework.test.bean.IUserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import cn.onenine.springframework.interceptor.UserServiceInterceptor;
import cn.onenine.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }


    @Test
    public void testPropertiesConfiguration(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);

        System.out.println("user token：" + userService.getToken());
    }


}
