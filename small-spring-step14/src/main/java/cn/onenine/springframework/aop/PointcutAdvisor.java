package cn.onenine.springframework.aop;

/**
 * Description：切点访问者
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 20:47
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
