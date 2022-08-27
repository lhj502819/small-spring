package cn.onenine.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * Description：类型转换接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 20:50
 */
public interface ConversionService {

    /**
     * 判断 sourceType 是否可以转换为 targetType
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * 将source对象类型转换为targetType类型
     */
    <T> T convert(Object source, Class<T> targetType);

}
