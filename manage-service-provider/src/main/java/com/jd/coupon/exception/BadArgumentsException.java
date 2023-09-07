package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Arguments invalid")
public class BadArgumentsException extends RuntimeException {
    public static final BadArgumentsException INSTANCE = new BadArgumentsException();
}
