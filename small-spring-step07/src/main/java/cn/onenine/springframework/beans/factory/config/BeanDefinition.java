package cn.onenine.springframework.beans.factory.config;

import cn.onenine.springframework.beans.factory.PropertyValues;

/**
 * Description：Bean定义
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class BeanDefinition {

    /**
     *将Object对象替换为CLass，为了将Bean的实例化操作放到容器中
     */
    private Class beanClass;

    /**
     * 属性集合
     */
    private PropertyValues propertyValues;

    /**
     * 填充属性后执行的方法名称
     */
    private String initMethodName;

    /**
     * 销毁之前执行的方法名
     */
    private String destroyMethodName;

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        //防止propertyValues为空
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
