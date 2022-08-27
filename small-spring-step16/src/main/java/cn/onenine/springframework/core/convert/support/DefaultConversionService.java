package cn.onenine.springframework.core.convert.support;

import cn.onenine.springframework.core.convert.converter.ConvertRegistry;

/**
 * Description：实现类型转换服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 20:49
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConvertRegistry convertRegistry){
        //添加各类类型转换工厂
        convertRegistry.addConverterFactory(new StringToNumberConverterFactory());

    }
}
