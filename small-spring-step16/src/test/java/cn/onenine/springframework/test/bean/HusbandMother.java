package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.beans.factory.FactoryBean;
import net.sf.cglib.proxy.Proxy;

/**
 * Description：婆婆类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 12:06
 */
public class HusbandMother implements FactoryBean<IMother> {
    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class},
                ((proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName()));
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
