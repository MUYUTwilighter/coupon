package com.jd.coupon.dao;

import com.jd.coupon.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface StaffDao extends JpaRepository<Staff, String> {
    @Query(nativeQuery = true,
    value = "select * from staff " +
        "where name like ?1" +
        "   and business like ?2" +
        "   and (?3 is null or auth = ?3) " +
        "limit ?4, 10")
    List<Staff> search(String name, String business, Short auth, Integer index);
}
