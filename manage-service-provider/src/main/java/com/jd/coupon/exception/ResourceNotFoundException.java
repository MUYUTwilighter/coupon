package com.jd.coupon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MUYU_Twilighter
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Request resource not found")
public class ResourceNotFoundException extends RuntimeException {
    public static final ResourceNotFoundException INSTANCE = new ResourceNotFoundException();
}
