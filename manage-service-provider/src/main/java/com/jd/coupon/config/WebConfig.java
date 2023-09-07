package com.jd.coupon.config;

import com.jd.coupon.entity.Staff;
import com.jd.coupon.interceptor.StaffAuthInterceptor;
import com.jd.coupon.interceptor.StaffLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author MUYU_Twilighter
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StaffAuthInterceptor(Staff.AUTH_STAFF)).
            addPathPatterns("manage/staff/search",
                "manage/staff/info",
                "manage/coupon");
        registry.addInterceptor(new StaffAuthInterceptor(Staff.AUTH_STAFF, "name")).
            addPathPatterns("manage/staff/request/post",
                "manage/staff/request/pending",
                "manage/coupon/request/pending");
        registry.addInterceptor(new StaffAuthInterceptor(Staff.AUTH_ADMIN)).
            addPathPatterns("manage/staff/approve",
                "manage/coupon/approve");
        registry.addInterceptor(new StaffLoginInterceptor()).
            addPathPatterns("manage/staff/login");
    }
}
