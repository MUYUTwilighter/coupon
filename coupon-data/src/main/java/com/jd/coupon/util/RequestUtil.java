package com.jd.coupon.util;

import com.jd.coupon.entity.Staff;

/**
 * @author MUYU_Twilighter
 */
public class RequestUtil {
    public static Long APPR_ONCE = Long.valueOf(Staff.AUTH_ADMIN);
    public static Long APPR_TWICE = (long) Staff.AUTH_ADMIN << 8 | Staff.AUTH_SYS_ADMIN;

    public static Byte CATE_STAFF_REGISTER = 0x00;
    public static Byte CATE_STAFF_DELETE = 0x01;
    public static Byte CATE_STAFF_CHANGE_BUS = 0x02;
    public static Byte CATE_STAFF_CHANGE_AUTH = 0x03;
    public static Byte CATE_COUPON_CREATE = 0x10;
    public static Byte CATE_COUPON_DELETE = 0x11;
    public static Byte CATE_COUPON_POST = 0x12;
    public static Byte CATE_COUPON_WITHDRAW = 0x13;
}
