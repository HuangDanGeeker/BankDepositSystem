package com.mybatisHandler.EnumHandler;

/**
 * Created by HuangDanGeeker on 2018/3/26.
 */
public class OperTypeUtil {
    public static <E extends Enum<?> & BasicCodeEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
