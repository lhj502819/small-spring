package cn.onenine.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/24 22:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.FIELD,
        ElementType.PARAMETER,ElementType.TYPE})
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
