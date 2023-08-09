package com.jd.coupon.service;

import com.jd.coupon.dao.StaffDao;
import com.jd.coupon.entity.Staff;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author MUYU_Twilighter
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    public Staff find(@NotNull String username) {
        Optional<Staff> optionalStaff = staffDao.findById(username);
        if (optionalStaff.isPresent()) {
            return optionalStaff.get().hide();
        } else {
            return Staff.EMPTY;
        }
    }

    @Override
    public List<Staff> search(String name, String business, Short auth, @NotNull int page) {
        name = name == null ? "%" : name;
        business = business == null ? "%" : business;
        List<Staff> results = staffDao.search(name, business, auth, page * 10);
        List<Staff> list = new ArrayList<>(results.size());
        for (Staff result : results) {
            list.add(result.hide());
        }
        return list;
    }

    @Override
    public Boolean modify(@NotNull String name, @Nullable String business, @Nullable Short auth, @Nullable String pwd) {
        Optional<Staff> optional = staffDao.findById(name);
        if (optional.isPresent()) {
            Staff staff = optional.get();
            if (business != null) {
                staff.setBusiness(business);
            }
            if (auth != null) {
                staff.setAuth(auth);
            }
            if (pwd != null) {
                staff.setPwd(pwd);
            }
            staffDao.save(staff);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean verify(String name, String pwd) {
        Optional<Staff> optional = staffDao.findById(name);
        if (optional.isPresent()) {
            Staff staff = optional.get();
            return Objects.equals(staff.getPwd(), pwd);
        } else {
            return false;
        }
    }

    @Override
    public Boolean register(@NotNull String name, @NotNull String pwd) {
        Optional<Staff> optional = staffDao.findById(name);
        if (optional.isPresent()) {
            return false;
        } else {
            Staff staff = new Staff();
            staff.setName(name);
            staff.setBusiness(Staff.BUS_EMPTY);
            staff.setAuth((short) 0);
            staff.setPwd(pwd);
            staffDao.save(staff);
            return true;
        }
    }

    @Override
    public void destroy(@NotNull String name) {
        this.staffDao.deleteById(name);
    }

    @Override
    public Staff find(@NotNull String name, @NotNull Short auth) {
        Staff staff = this.find(name);
        return staff.beyond(auth) ? staff : Staff.EMPTY;
    }
}
