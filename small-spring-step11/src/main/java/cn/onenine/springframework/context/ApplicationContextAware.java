package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.Aware;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 20:32
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
