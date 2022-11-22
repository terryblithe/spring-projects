package com.tb.test.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义转换器
 *
 * @author wp
 * @since 2022/11/19
 */
public class String2DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

}
