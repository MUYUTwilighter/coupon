package com.jd.coupon.controller;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.service.DistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@RestController
@RequestMapping("distribute")
public class DistributeController {
    @Autowired
    private DistributeService service;

    @GetMapping("interest")
    public synchronized List<Coupon> distributeInterest(
        @RequestParam("hobby") String hobby,
        @RequestParam("job") String job) {
        return service.distributeInterest(hobby, job, 10);
    }
}
