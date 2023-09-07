package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Request invalid")
public class BadRequestException extends RuntimeException {
    public static final BadRequestException INSTANCE = new BadRequestException();
}
