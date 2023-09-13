package com.jd.coupon.dao;

import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface StaffDao extends JpaRepository<Staff, String> {
    @Query(nativeQuery = true,
        value = "select * from staff " +
            "where (?1 is null or name like ?1) " +
            "   and (?2 is null or business like ?2) " +
            "   and (?3 is null or auth = ?3) " +
            "limit ?4, 10")
    List<Staff> search(@Nullable String name,
                       @Nullable String business,
                       @Nullable Short auth,
                       @NotNull Integer index);

    default List<StaffDto> searchHidden(@Nullable String name,
                                        @Nullable String business,
                                        @Nullable Short auth,
                                        @NotNull Integer index) {
        List<Staff> staffs = this.search(name, business, auth, index);
        return StaffDto.of(staffs);
    }

    @Query(nativeQuery = true,
        value = "select * from staff where name = ?1")
    Staff find(@NotNull String name);

    default StaffDto findHidden(@NotNull String name) {
        Staff staff = find(name);
        return StaffDto.of(staff);
    }

    @Query(nativeQuery = true,
        value = "update staff set business = ?2 where name = name")
    void updateBus(@NotNull String name, @NotNull String business);

    @Query(nativeQuery = true,
        value = "update staff set auth = ?2 where name = name")
    void updateAuth(@NotNull String name, @NotNull Byte auth);
}
