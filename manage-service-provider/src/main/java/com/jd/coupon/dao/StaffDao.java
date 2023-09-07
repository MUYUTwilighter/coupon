package com.jd.coupon.dao;

import com.jd.coupon.entity.Staff;
import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author MUYU_Twilighter
 */
public interface StaffDao extends JpaRepository<Staff, String> {
    @Query(nativeQuery = true,
        value = "select * from staff " +
            "where (?1 is null or name like ?1)" +
            "   and (?2 is null or business like ?2)" +
            "   and (?3 is null or auth = ?3) " +
            "limit ?4, 10")
    List<Staff> search(@Nullable String name,
                       @Nullable String business,
                       @Nullable Short auth,
                       @NotNull Integer index);

    @Query(nativeQuery = true,
        value = "select name, business, auth from staff " +
            "where (?1 is null or name like ?1)" +
            "   and (?2 is null or business like ?2)" +
            "   and (?3 is null or auth = ?3) " +
            "limit ?4, 10")
    List<Map<String, Object>> searchHiddenMap(@Nullable String name,
                                              @Nullable String business,
                                              @Nullable Short auth,
                                              @NotNull Integer index);

    default List<StaffDto> searchHidden(@Nullable String name,
                                        @Nullable String business,
                                        @Nullable Short auth,
                                        @NotNull Integer index) {
        List<Map<String, Object>> maps = this.searchHiddenMap(name, business, auth, index);
        return parse(maps);
    }

    @Query(nativeQuery = true,
        value = "select * from staff where name = ?1")
    Staff find(@NotNull String name);

    @Query(nativeQuery = true,
        value = "select name, business, auth from staff where name = ?1")
    Map<String, Object> findHiddenMap(@NotNull String name);

    @Query(nativeQuery = true,
        value = "update staff set business = ?2 where name = name")
    void updateBus(@NotNull String name, @NotNull String business);

    @Query(nativeQuery = true,
        value = "update staff set auth = ?2 where name = name")
    void updateAuth(@NotNull String name, @NotNull Byte auth);

    default StaffDto findHidden(@NotNull String name) {
        Map<String, Object> map = findHiddenMap(name);
        return parse(map);
    }

    private static List<StaffDto> parse(List<Map<String, Object>> maps) {
        return (List<StaffDto>) (Object) maps;
    }

    private static StaffDto parse(Map<String, Object> map) {
        return (StaffDto) map;
    }
}
