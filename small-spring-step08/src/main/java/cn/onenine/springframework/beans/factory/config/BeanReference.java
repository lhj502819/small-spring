package cn.onenine.springframework.beans.factory.config;

/**
 * Description：Bean引用
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/5
 */
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
