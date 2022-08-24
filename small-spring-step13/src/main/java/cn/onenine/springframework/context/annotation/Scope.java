package cn.onenine.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * Description：Bean的作用域注解，默认为单例
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/23 21:03
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
