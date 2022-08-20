package cn.onenine.springframework.context;

import cn.onenine.springframework.context.ApplicationEvent;

import java.util.EventListener;

/**
 * Description：事件监听者
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:06
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
