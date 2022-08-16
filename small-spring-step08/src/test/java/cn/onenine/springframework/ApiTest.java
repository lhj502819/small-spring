package cn.onenine.springframework;

import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

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
    public void test_prototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        //获取Bean对象调用方法
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        //xml中配置的scope为prototype，因此这里的对象应该是两个不同的
        System.out.println(userService1);
        System.out.println(userService2);

        //打印十六进制哈希
        System.out.println(userService1 + "十六进制哈希：" + Integer.toHexString(userService1.hashCode()));

        System.out.println(ClassLayout.parseInstance(userService1).toPrintable());
    }

    @Test
    public void test_factoryBean(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }


}
