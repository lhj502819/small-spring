package cn.onenine.springframework.core.convert.converter;

/**
 * Description：类型包装转换接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 20:27
 */
public interface Converter <S,T>{

    /**
     * 将S类型转换为T类型
     */
    T convert(S source);

}
