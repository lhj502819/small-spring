package cn.onenine.springframework;

import cn.onenine.springframework.aop.AdvisedSupport;
import cn.onenine.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.onenine.springframework.aop.MethodMatcher;
import cn.onenine.springframework.aop.TargetSource;
import cn.onenine.springframework.aop.framework.Cglib2AopProxy;
import cn.onenine.springframework.aop.framework.JdkDynamicAopProxy;
import cn.onenine.springframework.aop.framework.ReflectiveMethodInvocation;
import cn.onenine.springframework.bean.IUserService;
import cn.onenine.springframework.bean.UserService;
import cn.onenine.springframework.context.ClassPathXmlApplicationContext;
import cn.onenine.springframework.interceptor.UserServiceInterceptor;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

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
