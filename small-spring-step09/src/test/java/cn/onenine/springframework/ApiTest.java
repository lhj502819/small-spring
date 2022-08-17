package cn.onenine.springframework;

import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import cn.onenine.springframework.event.CustomEvent;
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
    public void test_event(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        applicationContext.publishEvent(new CustomEvent(applicationContext , 1001L , "你好吗?"));

        applicationContext.registerShutdownHook();


    }



}
