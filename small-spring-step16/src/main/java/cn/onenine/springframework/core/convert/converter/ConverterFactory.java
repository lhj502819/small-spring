package cn.onenine.springframework.core.convert.converter;

/**
 * Description：类型转换工厂
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 20:28
 */
public interface ConverterFactory<S,R> {
    /**
     * 获取能将S转换为T的converter，并且T是R的一个实例
     */
    <T extends  R> Converter<S,T> getConverter(Class<T> targetType);
}
