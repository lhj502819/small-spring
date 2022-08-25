package cn.onenine.springframework.context.event;

import cn.onenine.springframework.context.ApplicationContext;
import cn.onenine.springframework.context.ApplicationEvent;

/**
 * Description：应用上下文事件
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:02
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
