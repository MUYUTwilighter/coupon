package com.jd.coupon.interceptor;

import com.jd.coupon.component.AuthComponent;
import com.jd.coupon.service.StaffService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MUYU_Twilighter
 */
@Component
public class StaffAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private final Integer auth;
    @Autowired
    private AuthComponent authComponent;
    @Autowired
    private StaffService service;


    public StaffAuthInterceptor(@NotNull Integer auth) {
        this.auth = auth;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if (token == null) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return false;
        } else {
            return false;
        }
    }
}
