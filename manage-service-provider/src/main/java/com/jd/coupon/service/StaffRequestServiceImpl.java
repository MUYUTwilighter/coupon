package com.jd.coupon.service;

import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.dao.StaffRequestDao;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffRequest;
import com.jd.coupon.entity.StaffRequestDto;
import com.jd.coupon.exception.PermissionDenyException;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class StaffRequestServiceImpl implements StaffRequestService {
    private final Map<Byte, Predicate<StaffRequest>> executorMap = new HashMap<>();
    @Autowired
    private StaffRequestDao staffRequestDao;
    @Autowired
    private StaffDao staffDao;

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

    @Override
    public StaffRequestDto find(Long id) {
        return staffRequestDao.find(id);
    }

    @Override
    public Boolean approve(@NotNull Long id, @NotNull Byte auth) {
        StaffRequest request = staffRequestDao.find(id);
        if (request == null) {
            throw ResourceNotFoundException.INSTANCE;
        }
        if (!request.nextApproval().equals(auth)) {
            throw PermissionDenyException.INSTANCE;
        }
        request.rollApproval();
        if (request.nextApproval() == 0) {
            Predicate<StaffRequest> predicate = executorMap.get(request.getCategory());
            return predicate.test(request);
        }
        return true;
    }

    @Override
    public void withdraw(@NotNull Long id) {
        this.staffRequestDao.deleteById(id);
    }

    @Override
    public List<StaffRequestDto> search(@Nullable Byte category,
                                        @Nullable Date start,
                                        @Nullable Date end,
                                        @Nullable String initiator,
                                        @Nullable Boolean rejected,
                                        @Nullable Byte nextApprove,
                                        @Nullable Integer page) {
        page = page == null || page < 0 ? 0 : page;
        return this.staffRequestDao.search(category, start, end, initiator, rejected, nextApprove, page * 10);
    }
}
