package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.onenine.springframework.beans.factory.BeansException;
import cn.onenine.springframework.beans.factory.PropertyValue;
import cn.onenine.springframework.beans.factory.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        System.out.println("创建Bean实例");
        Object bean = null;
        try {
            //创建Bean对象
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean failed", e);
        }

        //填充bean属性
        applyPropertyValues(beanName, bean, beanDefinition);
        //添加到单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        //获取所有的构造器
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor<?> constructor : declaredConstructors) {
            //判断构造器的参数是不是和用户传入的参数一样
            //一样则使用该构造器创建Bean实例
            // 缺陷：缺少对参数类型的比对，如果参数的数量相同，但个数不相同，是会报错的
            if (args != null && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        //获取创建实例策略
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference){
                    //判断属性是否为其他Bean的引用
                    //A依赖B ，获取B的实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Error setting property values ："  + beanName);
        }
    }
}
