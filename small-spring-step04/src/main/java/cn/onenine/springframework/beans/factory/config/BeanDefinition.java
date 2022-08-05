package cn.onenine.springframework.beans.factory.config;

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

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
