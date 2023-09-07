package com.jd.coupon.controller;

import com.jd.coupon.entity.StaffDto;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.jd.coupon.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MUYU_Twilighter
 */
@RestController
@RequestMapping("staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("info/{name}")
    public StaffDto info(@PathVariable(name = "name") String name) {
        StaffDto staff = staffService.info(name);
        if (staff == null) {
            throw ResourceNotFoundException.INSTANCE;
        }
        return staff;
    }

    @PostMapping("register")
    public void register(HttpServletRequest request) {
        String initiator = request.getParameter("initiator");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        staffService.register(initiator, name, pwd);
    }
}
