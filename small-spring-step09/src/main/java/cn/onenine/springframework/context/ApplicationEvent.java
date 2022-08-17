package cn.onenine.springframework.context;

import java.util.EventObject;

/**
 * Description：应用事件基类，所有的事件都需要继承此类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:00
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
