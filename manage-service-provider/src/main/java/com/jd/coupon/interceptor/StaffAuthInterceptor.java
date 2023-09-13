package com.jd.coupon.interceptor;

import com.jd.coupon.component.AuthComponent;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MUYU_Twilighter
 */
public class StaffAuthInterceptor implements HandlerInterceptor {
    private final Byte auth;
    private final String target;
    @Autowired
    private AuthComponent authComponent;

    public StaffAuthInterceptor(@NotNull Byte auth) {
        this.auth = auth;
        this.target = null;
    }

    public StaffAuthInterceptor(@NotNull Byte auth, @NotNull String target) {
        this.auth = auth;
        this.target = target;
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if (token == null) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return false;
        }
        StaffDto initiator = authComponent.find(token);
        if (initiator == null) {
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return false;
        }

        if (initiator.hasAuth(auth)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return true;
        }
        if (this.target != null) {
            String target = request.getParameter(this.target);
            if (initiator.hasAuth(Staff.AUTH_STAFF) && initiator.getName().equals(target)) {
                return true;
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
