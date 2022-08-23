package cn.onenine.springframework.aop.framework;

import cn.onenine.springframework.aop.AdvisedSupport;

/**
 * Description：代理工厂，主要解决的是关于JDK和Cglib两种代理的选择问题，有了代理工厂，就可以按照不同的需求进行控制
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 21:08
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
