package cn.onenine.springframework.aop;

/**
 * Description：切点表达式
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:06
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
