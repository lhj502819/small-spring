package cn.onenine.springframework.aop;

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

    public Class<?>[] getTargetClass() {

        return target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
