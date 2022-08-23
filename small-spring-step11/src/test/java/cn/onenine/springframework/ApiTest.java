package cn.onenine.springframework;

import cn.onenine.springframework.test.bean.IUserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    @Test
    public void testAop() throws NoSuchMethodException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("结果为：" + userService.queryUserInfo() + "...............");;
    }




}
