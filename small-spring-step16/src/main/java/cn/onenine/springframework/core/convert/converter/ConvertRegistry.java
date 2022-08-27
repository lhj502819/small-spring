package cn.onenine.springframework.core.convert.converter;

/**
 * Description：类型转换注册接口
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 20:31
 */
public interface ConvertRegistry {

    void addConverter(Converter<?,?> converter);

    /**
     * 添加通用转换器
     */
    void addConverter(GenericConverter converter);

    void  addConverterFactory(ConverterFactory<?,?> converterFactory);
}
