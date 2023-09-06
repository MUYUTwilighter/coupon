package com.jd.coupon.service;

import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

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
    public Boolean register(@NotNull String name, @NotNull String pwd) {
        if (staffDao.existsById(name)
            || name.isEmpty() || name.length() >= 32 ||
            !name.matches(Staff.NAME_REGEX)) {
            return false;
        } else {
            Staff staff = new Staff();
            staff.setName(name);
            staff.setBusiness(Staff.BUS_EMPTY);
            staff.setAuth(Staff.AUTH_COMMON);
            staff.setPwd(pwd);
            staffDao.save(staff);
            return true;
        }
    }

    @Override
    public Boolean verifyPwd(@NotNull String name, @NotNull String pwd) {
        Staff staff = staffDao.find(name);
        return staff != null && pwd.equals(staff.getPwd());
    }

    @Override
    public Boolean changePwd(@NotNull String name, @NotNull String pwd, @NotNull String newPwd) {
        Staff staff = staffDao.find(name);
        if (staff == null || !verifyPwd(name, pwd)) {
            return false;
        }
        staff.setPwd(pwd);
        staffDao.save(staff);
        return true;
    }

    @Override
    public Boolean changeBusiness(@NotNull String name, @NotNull String newBusiness) {
        Staff staff = staffDao.find(name);
        if (staff == null) {
            return false;
        }
        staff.setBusiness(newBusiness);
        staffDao.save(staff);
        return true;
    }

    @Override
    public Boolean changeAuth(@NotNull String name, @NotNull Byte newAuth) {
        Staff staff = staffDao.find(name);
        if (staff == null) {
            return false;
        }
        staff.setAuth(newAuth);
        staffDao.save(staff);
        return true;
    }
}
