package cn.onenine.springframework.aop;

import cn.onenine.springframework.utils.ClassUtils;

/**
 * Description：目标代理对象包装类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 21:41
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取接口信息，需要针对通过Cglib动态代理生成的Class，先获取其父类
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = ClassUtils.isCglibProxyClass(target.getClass()) ?
                target.getClass().getSuperclass() :
                target.getClass();
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
