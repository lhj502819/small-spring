package cn.onenine.springframework.context.event;

import cn.onenine.springframework.context.ApplicationListener;

/**
 * Description：上下文刷新成功事件监听器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 22:57
 */
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("[ContextRefreshEventListener]监听到上下文刷新成功事件");
    }
}
