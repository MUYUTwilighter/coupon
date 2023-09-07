package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.dao.CouponRequestDao;
import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.dao.StaffRequestDao;
import com.jd.coupon.entity.*;
import com.jd.coupon.key.CouponId;
import com.jd.coupon.util.Executor;
import com.sun.istack.NotNull;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class StaffRequestServiceImpl {
    private final Map<Byte, Predicate<StaffRequest>> executorMap = new HashMap<>();
    @Autowired
    private StaffRequestDao staffRequestDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private CouponRequestDao couponRequestDao;
    @Autowired
    private CouponDao couponDao;

    public StaffRequestServiceImpl() {
        executorMap.put(StaffRequest.CATE_REGISTER, request -> {
            Staff staff = request.extractStaff();
            if (staffDao.existsById(staff.getName())) {
                return false;
            }
            staffDao.save(staff);
            return true;
        });
        executorMap.put(StaffRequest.CATE_DELETE, request -> {
            String name = request.getStaffName();
            staffDao.deleteById(name);
            return true;
        });
        executorMap.put(StaffRequest.CATE_CHANGE_BUS, request -> {
            String name = request.getStaffName();
            String business = request.getStaffBusiness();
            staffDao.updateBus(name, business);
            return true;
        });
        executorMap.put(StaffRequest.CATE_CHANGE_AUTH, request -> {
            String name = request.getStaffName();
            Byte auth = request.getStaffAuth();
            staffDao.updateAuth(name, auth);
            return true;
        });
    }

    public Boolean approve(@NotNull Byte auth, @NotNull Long id) {
        StaffRequest request = staffRequestDao.find(id);
        if (request == null) {
            return false;
        }
        if (!request.nextApproval().equals(auth)) {
            return false;
        }
        request.rollApproval();
        if (request.nextApproval() == 0) {
            Predicate<StaffRequest> predicate = executorMap.get(request.getCategory());
            return predicate.test(request);
        }
        return true;
    }
}
