package cn.onenine.springframework.beans.factory;

/**
 * Description：Bean销毁之前执行的方法
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/14 11:29
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
