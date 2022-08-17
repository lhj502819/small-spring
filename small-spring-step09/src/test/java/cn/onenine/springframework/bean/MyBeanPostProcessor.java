package cn.onenine.springframework.bean;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Description：自定义BeanPostProcessor
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/12 22:31
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
