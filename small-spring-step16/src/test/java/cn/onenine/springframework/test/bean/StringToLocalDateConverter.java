package cn.onenine.springframework.test.bean;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Description：字符串转日期类Converter
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/28 11:52
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String dateTimeStr) {
        if (StrUtil.isBlank(dateTimeStr)) {
            return LocalDate.now();
        }
        return  LocalDateTimeUtil.parseDate(dateTimeStr, DatePattern.NORM_DATE_PATTERN);
    }
}
