package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Bad token or not enough authentication level")
public class PermissionDenyException extends RuntimeException {
    public static final PermissionDenyException INSTANCE = new PermissionDenyException();
}
