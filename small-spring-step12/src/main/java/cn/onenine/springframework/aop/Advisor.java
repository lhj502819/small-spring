package cn.onenine.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Description：访问者，承担了Pointcut和Advice的组合，Pointcut用户获取JoinPoint，而Advice决定于JoinPoint执行什么操作
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 20:37
 */
public interface Advisor {

    Advice getAdvice();

}
