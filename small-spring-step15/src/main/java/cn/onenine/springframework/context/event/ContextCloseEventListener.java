package cn.onenine.springframework.context.event;

import cn.onenine.springframework.context.ApplicationListener;

/**
 * Description：上下文关闭事件监听器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 22:58
 */
public class ContextCloseEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("[ContextCloseEventListener]监听到上下文关闭事件");
    }
}
