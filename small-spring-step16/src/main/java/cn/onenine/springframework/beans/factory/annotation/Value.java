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
@Target({ElementType.METHOD,ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface Value {
    /**
     * 使用占位符 #{xx}
     */
    String value();
}
