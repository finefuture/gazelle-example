package org.gra4j.gazelleExample.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericTypeUtils {
    /**
     * 获取真实泛型类型
     * @param genericType
     * @return
     */
    public static Class<?>[] getGenericType(Type genericType) {
        if (genericType != null && genericType instanceof ParameterizedType) { // 如果不为空并且是泛型参数的类型
            ParameterizedType pt = (ParameterizedType) genericType;
            Type[] types = pt.getActualTypeArguments();
            if (types != null && types.length > 0) {
                Class<?>[] classes = new Class<?>[types.length];
                for (int i = 0; i < classes.length; i++) {
                    classes[i] = (Class<?>) types[i];
                }
                return classes;
            }
        }
        return null;
    }

    private GenericTypeUtils() {
    }
}
