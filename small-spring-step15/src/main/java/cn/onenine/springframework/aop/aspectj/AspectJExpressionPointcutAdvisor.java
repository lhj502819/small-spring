package cn.onenine.springframework.aop.aspectj;

import cn.onenine.springframework.aop.Pointcut;
import cn.onenine.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Description：还是一个包装类，实现了PointcutAdvisor接口
 *  把切面pointcut、拦截方法和具体的拦截表达式包装在一起
 *  这样就可以在xml的配置中定义pointAdvisor切面拦截器了
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/21 20:50
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    //切面，主要用于判断Class/Method是否满足切面表达式
    private AspectJExpressionPointcut pointcut;
    //具体的拦截方法
    private Advice advice;
    //表达式
    private String expression;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if(pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
