package cn.onenine.springframework.aop.framework;

/**
 * Description：用于代理类，具体的实现方式有多种，JDK、Cglib
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:37
 */
public interface AopProxy {
    Object getProxy();
}
