package cn.onenine.springframework.beans.factory.config;

import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.ConfigurableBeanFactory;

/**
 * Description：Bean定义
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

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

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = scope.equals(ConfigurableBeanFactory.SCOPE_SINGLETON);
        this.prototype = !singleton;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

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
