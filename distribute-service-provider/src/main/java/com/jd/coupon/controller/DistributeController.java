package com.jd.coupon.controller;

import com.jd.coupon.entity.CouponDto;
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

    @GetMapping("available")
    public List<CouponDto> searchAvailable(
        @RequestParam(value = "business", required = false) String business,
        @RequestParam(value = "page", required = false) Integer page
    ) {
        return service.searchAvailable(business, page);
    }

    @GetMapping("interest")
    public List<CouponDto> searchInterest(
        @RequestParam("hobby") String hobby,
        @RequestParam("job") String job,
        @RequestParam(value = "page", required = false) Integer page
    ) {
        return service.searchInterest(hobby, job, page);
    }
}
