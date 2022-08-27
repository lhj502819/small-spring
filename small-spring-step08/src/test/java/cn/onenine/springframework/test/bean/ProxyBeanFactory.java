package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.beans.factory.FactoryBean;
import cn.onenine.springframework.test.dao.IUserDao;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/16 21:38
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String,String> hashMap = new HashMap<>();
            hashMap.put("10001" , "壹玖1");
            hashMap.put("10002" , "壹玖2");
            hashMap.put("10003" , "壹玖3");
            return "你被代理了：" + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{IUserDao.class},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
