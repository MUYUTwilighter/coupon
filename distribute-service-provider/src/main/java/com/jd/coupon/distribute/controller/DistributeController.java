package com.jd.coupon.distribute.controller;

import com.jd.coupon.distribute.entity.CouponDto;
import com.jd.coupon.distribute.service.DistributeService;
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

    @GetMapping("search")
    public List<CouponDto> searchAvailable(
        @RequestParam(value = "business", required = false) String business,
        @RequestParam(value = "page", required = false) Integer page
    ) {
        return service.searchAvailable(business, page);
    }
}
