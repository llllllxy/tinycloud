package org.liuxingyu.tinycloud.common.annotation;

import org.liuxingyu.tinycloud.common.validator.TimeFormatValidator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <p>
 *  Hibernate validator 自定义校验器注解
 *  用于校验日期和时间字符串是否符合指定格式
 *  使用方法，在需要校验的dto字段上加@TimeFormat即可
 * </p >
 *
 * @author liuxingyu01
 * @since 2023-03-20 09:09:38
 */
@Documented
@Constraint(validatedBy = TimeFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeFormatCheck {

    /**
     * 日期格式
     */
    String format() default "yyyy-MM-dd";

    /**
     * 是否允许为空，true允许，false 不允许
     */
    boolean canBeNull() default true;

    /**
     * 校验不通过时的报错信息
     */
    String message() default "日期字符串格式不合法！";

    /**
     * 将validator进行分类，不同的类group中会执行不同的validator操作
     *
     * @return validator的分类类型
     */
    Class<?>[] groups() default {};

    /**
     * 主要是针对bean，很少使用
     *
     * @return 负载
     */
    Class<? extends Payload>[] payload() default {};
}