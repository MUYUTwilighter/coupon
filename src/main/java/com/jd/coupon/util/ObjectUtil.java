package com.jd.coupon.util;

/**
 * @author MUYU_Twilighter
 */
public class ObjectUtil {
    public static boolean allNotNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean allNull(Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean anyNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyNotNull(Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
