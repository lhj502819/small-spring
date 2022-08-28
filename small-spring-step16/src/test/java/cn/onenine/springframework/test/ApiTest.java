package cn.onenine.springframework.test;

import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import cn.onenine.springframework.test.bean.Husband;
import org.junit.Test;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class ApiTest {

    @Test
    public void testPropertyConverter() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println(husband);
    }


}
