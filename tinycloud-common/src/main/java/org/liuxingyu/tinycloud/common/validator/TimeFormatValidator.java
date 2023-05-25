package org.liuxingyu.tinycloud.common.validator;

import org.apache.commons.lang3.StringUtils;
import org.liuxingyu.tinycloud.common.annotation.TimeFormatCheck;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  Hibernate validator 自定义校验器
 *  用于校验日期和时间字符串是否符合指定格式
 * </p >
 *
 * @author liuxingyu01
 * @since 2023-03-20 09:09:38
 */
public class TimeFormatValidator implements ConstraintValidator<TimeFormatCheck, String> {

    // 是否允许为空
    private boolean canBeNull;

    // 日期格式
    private String format;

    @Override
    public void initialize(TimeFormatCheck constraintAnnotation) {
        this.canBeNull = constraintAnnotation.canBeNull();
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return isValidDate(name, format, canBeNull);
    }


    /**
     * 校验时间字符串是否为正确的日期格式yyyy-MM-dd
     *
     * @param str       日期字符串
     * @param formatStr 日期格式
     * @param canBeNull 是否允许为null，true允许，false 不允许
     * @return 正确/不正确
     */
    private boolean isValidDate(String str, String formatStr, boolean canBeNull) {
        // 如果是空字符串的话，也返回true
        if (StringUtils.isEmpty(str)) {
            if (canBeNull) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!(str.length() == formatStr.length())) {
                return false;
            }
        }
        boolean result = true;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            result = false;
        }
        return result;
    }
}