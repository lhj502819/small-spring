package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.DisposableBean;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * Description：销毁方法适配器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 11:49
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //实现接口(DisposableBean)的方式
        if (bean instanceof DisposableBean) {
            //执行销毁方法
            ((DisposableBean)bean).destroy();
        }

        //xml定义destroy-method方式
        // !(bean instanceof DisposableBean) 防止二次执行销毁方法
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean)){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(destroyMethod == null){
                throw new BeansException("未找到destroy method：" + destroyMethodName + "，beanName：" + beanName);
            }
            destroyMethod.invoke(bean);
        }
    }
}
