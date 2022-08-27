package cn.onenine.springframework.core.convert.support;

import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.core.convert.converter.Converter;
import cn.onenine.springframework.core.convert.converter.ConverterFactory;
import cn.onenine.springframework.utils.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * Description：字符串转数字Converter
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 22:33
 */
public class StringToNumberConverterFactory implements ConverterFactory<String,Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }


    private static final class StringToNumber<T extends Number> implements Converter<String,T>{

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Nullable
        @Override
        public T convert(String source) {
            if (StrUtil.isBlank(source)) {
                return null;
            }
            return NumberUtils.parseNumber(source,this.targetType);
        }
    }
}
