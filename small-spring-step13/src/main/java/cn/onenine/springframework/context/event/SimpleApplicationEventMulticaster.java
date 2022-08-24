package cn.onenine.springframework.context.event;

import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.context.ApplicationEvent;
import cn.onenine.springframework.context.ApplicationListener;

/**
 * Description：Simple EventMulticaster
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 22:27
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {

            listener.onApplicationEvent(event);
        }
    }
}
