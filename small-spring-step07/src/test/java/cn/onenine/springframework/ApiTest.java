package cn.onenine.springframework;

import cn.onenine.springframework.test.bean.UserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    /**
     * 使用应用上下文
     */
    @Test
    public void testWithApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println(userService.getApplicationContext());
    }


}
