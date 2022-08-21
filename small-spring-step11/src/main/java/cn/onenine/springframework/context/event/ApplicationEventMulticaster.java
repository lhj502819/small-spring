package cn.onenine.springframework.context.event;

import cn.onenine.springframework.context.ApplicationEvent;
import cn.onenine.springframework.context.ApplicationListener;

/**
 * Description：事件广播器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:05
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听者
     * @param listener
     */
    void addApplicationLister(ApplicationListener<?> listener);

    /**
     * 移除事件监听者
     * @param listener
     */
    void removeApplicationLister(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
