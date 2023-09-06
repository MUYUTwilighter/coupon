package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Argument invalid")
public class BadArgumentException extends RuntimeException {
    public static final BadArgumentException INSTANCE = new BadArgumentException();
}
