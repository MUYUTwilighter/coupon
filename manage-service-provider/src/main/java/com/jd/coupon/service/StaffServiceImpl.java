package com.jd.coupon.service;

import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.dao.StaffRequestDao;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffDto;
import com.jd.coupon.entity.StaffRequest;
import com.jd.coupon.exception.BadArgumentsException;
import com.jd.coupon.exception.DuplicatedPostException;
import com.jd.coupon.exception.PermissionDenyException;
import com.jd.coupon.exception.ResourceNotFoundException;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private StaffRequestDao staffRequestDao;

    @Override
    public StaffDto info(@NotNull String name) {
        return staffDao.findHidden(name);
    }

    @Override
    public List<StaffDto> search(@Nullable String name,
                                 @Nullable String business,
                                 @Nullable Short auth,
                                 @Nullable Integer page) {
        page = (page == null) || (page < 0) ? 0 : page;
        return staffDao.searchHidden(name, business, auth, page * 10);
    }

    @Override
    public void register(@NotNull String initiator, @NotNull String name, @NotNull String pwd) {
        if (staffDao.existsById(name)) {
            throw DuplicatedPostException.INSTANCE;
        }
        if (name.isEmpty() || name.length() >= 32 || !name.matches(Staff.NAME_REGEX)) {
            throw BadArgumentsException.INSTANCE;
        }
        StaffRequest request = StaffRequest.initRegister(initiator, name, pwd);
        staffRequestDao.save(request);
    }

    public void delete(@NotNull String initiator, @NotNull String name) {
        StaffRequest request = StaffRequest.initDelete(initiator, name);
        staffRequestDao.save(request);
    }

    @Override
    public void verifyPwd(@NotNull String name, @NotNull String pwd) {
        Staff staff = staffDao.find(name);
        if (staff == null) {
            throw ResourceNotFoundException.INSTANCE;
        }
        if (!pwd.equals(staff.getPwd())) {
            throw PermissionDenyException.INSTANCE;
        }
    }

    @Override
    public void changePwd(@NotNull String name, @NotNull String pwd, @NotNull String newPwd) {
        Staff staff = staffDao.find(name);
        if (staff == null) {
            throw ResourceNotFoundException.INSTANCE;
        }
        if (!pwd.equals(staff.getPwd())) {
            throw PermissionDenyException.INSTANCE;
        }
        staff.setPwd(pwd);
        staffDao.save(staff);
    }

    @Override
    public Boolean changeBusiness(@NotNull String initiator, @NotNull String name, @NotNull String newBusiness) {
        if (!staffDao.existsById(name)) {
            return false;
        }
        StaffRequest request = StaffRequest.initChangeBus(initiator, name, newBusiness);
        staffRequestDao.save(request);
        return true;
    }

    @Override
    public Boolean changeAuth(@NotNull String initiator, @NotNull String name, @NotNull Byte newAuth) {
        if (!staffDao.existsById(name)) {
            return false;
        }
        StaffRequest request = StaffRequest.initChangeAuth(initiator, name, newAuth);
        staffRequestDao.save(request);
        return true;
    }
}
