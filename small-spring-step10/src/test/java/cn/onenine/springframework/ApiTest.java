package cn.onenine.springframework;

import cn.onenine.springframework.aop.AdvisedSupport;
import cn.onenine.springframework.aop.AspectJExpressionPointcut;
import cn.onenine.springframework.aop.MethodMatcher;
import cn.onenine.springframework.aop.TargetSource;
import cn.onenine.springframework.aop.framework.Cglib2AopProxy;
import cn.onenine.springframework.aop.framework.ReflectiveMethodInvocation;
import cn.onenine.springframework.bean.IUserService;
import cn.onenine.springframework.bean.UserService;
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
    public void test_proxy_method(){
        //目标对象（可以替换成任何的目标对象）
        Object targetObj = new UserService();

        //AOP代理
        IUserService userService = (IUserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            //方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.onenine.springframework.bean.UserService.*(..))");
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    //方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        String result = userService.queryUserInfo();
        System.out.println("测试结果："  + result);

    }

    @Test
    public void test_dynamic(){
        //目标对象
        IUserService userService = new UserService();

        //组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.onenine.springframework.bean.IUserService.*(..))"));

//        //代理对象（JdkDynamicAopProxy）
//        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
//        //测试调用
//        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        //代理对象（CglibDynamicAopProxy）
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));


    }



}
