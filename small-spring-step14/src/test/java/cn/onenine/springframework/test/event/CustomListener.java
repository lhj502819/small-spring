package cn.onenine.springframework.test.event;

import cn.onenine.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * Description：自定义事件监听器，监听CustomEvent
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 22:51
 */
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("[CustomListener]收到：" + event.getSource() + "消息，时间：" + new Date());

        System.out.println("[CustomListener]消息：" + event.getId() + "," + event.getMessage());
    }
}
