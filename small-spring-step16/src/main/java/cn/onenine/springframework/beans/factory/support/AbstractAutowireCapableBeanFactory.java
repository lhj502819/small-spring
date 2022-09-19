package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import cn.onenine.springframework.beans.factory.BeanNameAware;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValue;
import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.*;
import cn.onenine.springframework.beans.factory.config.*;
import cn.onenine.springframework.core.convert.ConversionService;

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

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            //执行实例化前的操作
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if (bean != null) {
                return bean;
            }
            //创建Bean对象
            bean = createBeanInstance(beanDefinition, beanName, args);

            //处理循环依赖，将实例化的对象放到缓存中
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletFactory(beanName, () -> getEarlyBeanReference(beanName, finalBean));
            }

            //实例化后判断，是否继续执行
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);

            if(!continueWithPropertyPopulation){
                return bean;
            }

            //在填充Bean属性之前，允许BeanPostProcessor修改属性值，比如解析@Autowired和@Value注解，其实也是填充Bean属性，只不过是处理注解的自动注入
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            //填充bean属性，处理xml配置的属性注入
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean " + beanName + " failed", e);
        }


        Object exposedObject = bean;
            if (beanDefinition.isSingleton()) {
            //注册销毁方法
            registerDisposableBeanInNecessary(beanName, bean, beanDefinition);

            exposedObject = getSingleton(beanName);
            //添加到单例对象缓存中
            registerSingleton(beanName, exposedObject);
        }
        return exposedObject;
    }

    protected Object getEarlyBeanReference(String beanName, Object bean) {
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(bean, beanName);
            }
        }

        return exposedObject;
    }

    private void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues propertyValues = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (propertyValues != null) {
                    for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }


            }
        }
    }

    @Deprecated
    private Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        //执行InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
        Object bean = applyBeanPostProcessBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null) {
            //不为空则说明创建了代理对象
            //执行applyBeanPostProcessorsAfterInitialization
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    private Object applyBeanPostProcessBeforeInstantiation(Class beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                //执行具体的逻辑，在AOP中则是创建代理对象
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    /**
     * 对于实例化之后返回false的对象，不再执行后续的填充属性的操作
     */
    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if(!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean,beanName)){
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;

    }

    void registerDisposableBeanInNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {

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
                }else {
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(),name);
                    //如果不是引用类型，则进行类型转换
                    if(sourceType != targetType){
                        ConversionService conversionService = getConversionService();
                        if (conversionService != null) {
                            if (conversionService.canConvert(sourceType,targetType)) {
                                value = conversionService.convert(value,targetType);
                            }
                        }
                    }
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
