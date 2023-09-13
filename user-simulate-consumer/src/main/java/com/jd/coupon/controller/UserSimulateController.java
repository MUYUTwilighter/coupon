package com.jd.coupon.controller;

import com.jd.coupon.service.CouponService;
import com.jd.coupon.service.DistributeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author MUYU_Twilighter
 */
@RestController
public class UserSimulateController {
    Logger logger = LogManager.getLogger(UserSimulateController.class);

    @Reference
    private DistributeService distributeService;
    @Reference
    private CouponService couponService;

    public void testDistribute(@RequestParam(value = "hobby", required = false) String hobby,
                               @RequestParam(value = "job", required = false) String job,
                               @RequestParam(value = "batch") Integer batch,
                               @RequestParam(value = "batch_size") Integer batchSize,
                               @RequestParam(value = "samples") Integer samples) {
        logger.info("Initializing interest data");


        long cost = 0L, begin, end;
        for (int i = 0; i < batch; ++i) {
            begin = Date.valueOf(LocalDate.now()).getTime();
            distributeService.distributeInterest(hobby, job, batchSize);
            end = Date.valueOf(LocalDate.now()).getTime();
            cost = end - begin;
        }
        logger.info("Run %d batches (%d count each), total %d ms, avg %f ms".formatted(batch, batchSize, cost, (float) cost / batch));
    }
}
