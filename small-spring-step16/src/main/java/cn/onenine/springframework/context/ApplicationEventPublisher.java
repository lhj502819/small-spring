package cn.onenine.springframework.context;

/**
 * Description：事件发布器，所有事件都需要从这里发出
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:46
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
