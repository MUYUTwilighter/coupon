package com.jd.coupon.util;

import com.jd.coupon.entity.RequestDto;

/**
 * @author MUYU_Twilighter
 */
public interface Executor<T extends RequestDto> {
    boolean run(T request);
}
