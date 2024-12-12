package org.tinycloud.common.utils;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * <p>
 * MyBatis-Plus动态获取SFunction公共方法，常用于动态排序
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-05-24 10:00:38
 */
public class LambdaUtils {
    private static final Logger logger = LoggerFactory.getLogger(LambdaUtils.class);


    public static final Map<Class<?>, PropertyDescriptor[]> cache = new ConcurrentHashMap<>();

    public static <T> SFunction<T, ?> getLambdaGetter(Class<T> clazz, String prop) {
        try {
            PropertyDescriptor[] beanGetters;
            if (cache.containsKey(clazz)) {
                beanGetters = cache.get(clazz);
            } else {
                beanGetters = ReflectUtils.getBeanGetters(clazz);
                cache.put(clazz, beanGetters);
            }

            MethodHandles.Lookup lookup = MethodHandles.lookup();
            Optional<PropertyDescriptor> optional = Arrays.stream(beanGetters)
                    .filter(pd -> pd.getName().equals(prop))
                    .findFirst();
            if (optional.isPresent()) {
                // 反射获取getter方法
                Method readMethod = optional.get().getReadMethod();
                // 拿到方法句柄
                final MethodHandle methodHandle = lookup.unreflect(readMethod);
                // 创建动态调用链
                CallSite callSite = LambdaMetafactory.altMetafactory(
                        lookup,
                        "apply",
                        MethodType.methodType(SFunction.class),
                        MethodType.methodType(Object.class, Object.class),
                        methodHandle,
                        MethodType.methodType(readMethod.getReturnType(), clazz),
                        LambdaMetafactory.FLAG_SERIALIZABLE
                );
                return (SFunction<T, ?>) callSite.getTarget().invokeExact();
            }
        } catch (Throwable throwable) {
            logger.error("getLambdaGetter Throwable: ", throwable);
        }

        return null;
    }

}
