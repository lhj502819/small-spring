package cn.onenine.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * Description：标识注解，用于给Spring扫描注册到Bean容器中
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/23 21:03
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
