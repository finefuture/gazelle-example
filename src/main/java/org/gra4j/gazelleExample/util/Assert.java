package org.gra4j.gazelleExample.util;

/**
 * Created by Administrator on 2017/11/8.
 */
public abstract class Assert {

    public static void notNull(Object object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message);
    }

    public static void isNull(Object object, String message) {
        if (object != null)
            throw new IllegalArgumentException(message);
    }

    public static void isNum(Object object, String message) {
        if (object instanceof Number)
            return;
        else
            throw new IllegalArgumentException(message);
    }

}
