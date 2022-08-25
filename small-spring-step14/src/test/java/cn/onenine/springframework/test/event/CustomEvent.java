package cn.onenine.springframework.test.event;

import cn.onenine.springframework.context.event.ApplicationContextEvent;

/**
 * Description：自定义事件
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 22:50
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source,Long id ,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
