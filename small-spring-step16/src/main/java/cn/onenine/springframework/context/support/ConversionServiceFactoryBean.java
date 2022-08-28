package cn.onenine.springframework.context.support;

import cn.onenine.springframework.beans.factory.FactoryBean;
import cn.onenine.springframework.beans.factory.InitializingBean;
import cn.onenine.springframework.core.convert.ConversionService;
import cn.onenine.springframework.core.convert.converter.ConvertRegistry;
import cn.onenine.springframework.core.convert.converter.Converter;
import cn.onenine.springframework.core.convert.converter.ConverterFactory;
import cn.onenine.springframework.core.convert.converter.GenericConverter;
import cn.onenine.springframework.core.convert.support.DefaultConversionService;
import cn.onenine.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * Description：类型转换工厂，通过FactoryBean就可以完成工程对象的操作，可以提供出转换对象的服务GenericConversionService，
 *  在afterPropertiesSet中调用了注册转换操作的类，最终这个类会被配置到spring.xml中在启动的过程加载
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 22:39
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters,conversionService);
    }

    private void registerConverters(Set<?> converters, ConvertRegistry registry){
        if (converters != null) {
            for (Object converter : converters) {
                if(converter instanceof GenericConverter){
                    registry.addConverter((GenericConverter) converter);
                }else if(converter instanceof Converter<?,?>){
                    registry.addConverter((Converter<?,?>)converter);
                }else if(converter instanceof ConverterFactory<?,?>){
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                }else {
                    throw new IllegalArgumentException("Each converter object must implement one of the Converter , ConverterFactory, or GenericConverter interfaces" );
                }
            }
        }
    }

    public void setConverters(Set<?> converters){
        this.converters = converters;
    }

}
