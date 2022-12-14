package cn.onenine.springframework.utils;

/**
 * Description：Class工具类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 22:51
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {

        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }

        return cl;

    }
}
