package com.jd.coupon.controller;

import com.jd.coupon.entity.StaffDto;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.jd.coupon.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Boolean register() {
        return true;
    }
}
