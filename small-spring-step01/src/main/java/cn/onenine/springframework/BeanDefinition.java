package cn.onenine.springframework;

/**
 * Description：Bean定义
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
