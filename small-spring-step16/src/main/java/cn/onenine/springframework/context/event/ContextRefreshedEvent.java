package cn.onenine.springframework.context.event;

/**
 * Description：应用上下文刷新事件
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:03
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
