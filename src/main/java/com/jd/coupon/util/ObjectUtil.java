package com.jd.coupon.util;

/**
 * @author MUYU_Twilighter
 */
public class ObjectUtil {
    public static boolean anyNull(Object ...objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyNotNull(Object ...objects) {
        for (Object obj : objects) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
