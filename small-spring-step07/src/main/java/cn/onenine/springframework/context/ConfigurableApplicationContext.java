package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.factory.BeansException;

/**
 * Description：继承自 {@link  ApplicationContext} ，并提供了refresh这个核心方法
 *  接下来就需要在上下文的实现中完成刷新容器的操作过程
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:49
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新上下文
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
