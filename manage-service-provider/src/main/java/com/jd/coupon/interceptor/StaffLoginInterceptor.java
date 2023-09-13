package com.jd.coupon.interceptor;

import com.jd.coupon.component.AuthComponent;
import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MUYU_Twilighter
 */
public class StaffLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthComponent authComponent;
    @Autowired
    private StaffDao staffDao;

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @Nonnull HttpServletResponse response,
                                @Nullable Object handler,
                                @Nullable Exception ex) {
        String name = request.getParameter("name");
        if (ex != null) {
            StaffDto staff = staffDao.findHidden(name);
            HttpSession session = request.getSession();
            String token = authComponent.putAndToken(staff);
            session.setAttribute("token", token);
        }
    }
}
