package cn.onenine.springframework.beans.factory.support;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Description：Cglib实例化
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/4
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(null == ctor) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
