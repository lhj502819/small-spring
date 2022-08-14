package cn.onenine.springframework.context;

import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.ListableBeanFactory;

/**
 * Description：继承了{@link  ListableBeanFactory} ，也就继承了关于{@link BeanFactory}方法
 *   ApplicationContext本身是 Central接口，但目前还不需要添加一些获取ID和父类上下文，所以暂时没有接口方法的定义
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 21:13
 */
public interface ApplicationContext extends ListableBeanFactory {

}
