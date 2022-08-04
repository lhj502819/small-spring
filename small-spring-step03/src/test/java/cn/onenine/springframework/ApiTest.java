package cn.onenine.springframework;

import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.lang.reflect.Constructor;

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

        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "壹玖");
        System.out.println(userService.queryUserInfo());

    }

    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> declaredConstructor = declaredConstructors[1];
        Constructor<UserService> constructor = beanClass.getDeclaredConstructor(declaredConstructor.getParameterTypes());
        UserService userService = constructor.newInstance("壹玖");
        System.out.println(userService.queryUserInfo());
    }

}
