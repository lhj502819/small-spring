package cn.onenine.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.BeanFactoryAware;
import cn.onenine.springframework.beans.factory.ConfigurableBeanFactory;
import cn.onenine.springframework.beans.factory.annotation.Autowired;
import cn.onenine.springframework.beans.factory.annotation.Qualifier;
import cn.onenine.springframework.beans.factory.annotation.Value;
import cn.onenine.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.onenine.springframework.core.convert.ConversionService;
import cn.onenine.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/24 22:17
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) {
        //处理@Value注解
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        //获取所有的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation !=null) {
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);

                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(),beanName);
                //如果不是引用类型，则进行类型转换
                if(sourceType != targetType){
                    ConversionService conversionService = beanFactory.getConversionService();
                    if (conversionService != null) {
                        if (conversionService.canConvert(sourceType,targetType)) {
                            value = conversionService.convert(value,targetType);
                        }
                    }
                }

                BeanUtil.setFieldValue(bean,field.getName(),value);
            }
        }

        //处理@Autowired注解
        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = fieldType.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (qualifierAnnotation != null) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName,fieldType);
                }else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean,field.getName(),dependentBean);

            }
        }
        return propertyValues;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }
}
