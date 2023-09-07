package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Resource to post conflicts with existing data")
public class DuplicatedPostException extends RuntimeException {
    public static final DuplicatedPostException INSTANCE = new DuplicatedPostException();
}
