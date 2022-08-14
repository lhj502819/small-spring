package cn.onenine.springframework;

import cn.onenine.springframework.beans.factory.Aware;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 20:32
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);

}
