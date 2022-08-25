package cn.onenine.springframework.aop;

/**
 * Description：类过滤器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:07
 */
public interface ClassFilter {

    /**
     * 判断是否符合表达式规则
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);

}
