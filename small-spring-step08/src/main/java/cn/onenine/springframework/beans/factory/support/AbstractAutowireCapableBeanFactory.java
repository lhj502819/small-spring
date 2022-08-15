package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.BeanNameAware;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValue;
import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.*;
import cn.onenine.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanPostProcessor;
import cn.onenine.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Description：在Bean创建时完成前置和后置处理
 * 实现BeanPostProcessor接口后，会涉及到两国接口方法，postProcessBeforeInitialization、postProcessAfterInitialization
 * 分别作用于Bean对象执行初始化前后的额外处理
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            //创建Bean对象
            bean = createBeanInstance(beanDefinition, beanName, args);
            //填充bean属性
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean failed", e);
        }

        //注册销毁方法
        registerDisposableBeanInNecessary(beanName, bean, beanDefinition);

        if (beanDefinition.isSingleton()) {
            //添加到单例对象缓存中
            addSingleton(beanName, bean);
        }
        return bean;
    }

    void registerDisposableBeanInNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        //非Singleton Bean不执行销毁方法
        if (!beanDefinition.isSingleton()) {
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registryDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }


    /**
     * 执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        //初始化Bean之前执行 Aware方法
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        //1.执行BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //2.执行初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("执行初始化方法异常...", e);
        }
        //3.执行BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 执行初始化方法
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //通过实现接口InitializingBean的方式执行初始化方法
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        //通过在xml配置init-method方法
        String initMethodName = beanDefinition.getInitMethodName();
        //!(bean instanceof InitializingBean) 主要为了防止二次执行初始化，也就是如果两种方式都使用，只会执行实现接口的方式初始化方法
        if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) {
            //私有方法也可执行，或者约定用户必须声明public方法
            Method initMethod = beanDefinition.getBeanClass().getDeclaredMethod(initMethodName);
            initMethod.setAccessible(true);
            if (initMethod == null) {
                throw new BeansException("未找到 init-method ，方法名为：" + initMethodName +
                        "，bean名称为：" + beanName);
            }
            initMethod.invoke(bean);
        }
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

                if (value instanceof BeanReference) {
                    //判断属性是否为其他Bean的引用
                    //A依赖B ，获取B的实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Error setting property values ：" + beanName);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor postProcessor : getBeanPostProcessors()) {
            Object current = postProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor postProcessor : getBeanPostProcessors()) {
            Object current = postProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
