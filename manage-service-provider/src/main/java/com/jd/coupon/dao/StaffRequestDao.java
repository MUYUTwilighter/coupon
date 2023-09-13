package com.jd.coupon.dao;

import com.jd.coupon.entity.StaffRequest;
import com.jd.coupon.entity.StaffRequestDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface StaffRequestDao extends JpaRepository<StaffRequest, Long> {
    @Query(nativeQuery = true,
        value = "select * from staff_request where id = ?1")
    StaffRequest find(@NotNull Long id);

    default StaffRequestDto findHidden(@NotNull Long id) {
        StaffRequest request = find(id);
        return StaffRequestDto.of(request);
    }

    @Query(nativeQuery = true, value =
        "select * from staff_request " +
            "where (?1 is null or category = ?1)" +
            "   and (?2 is null or start <= initiate)" +
            "   and (?3 is null or end >= initiate)" +
            "   and (?4 is null or initiator like ?4)" +
            "   and (?5 is null or rejected = ?5)" +
            "   and (?6 is null or approval & 0xFF = ?6) " +
            "limit ?7, 10")
    List<StaffRequest> search(@Nullable Byte category,
                              @Nullable Date start,
                              @Nullable Date end,
                              @Nullable String initiator,
                              @Nullable Boolean rejected,
                              @Nullable Byte nextApproval,
                              @NotNull Integer index);


    default List<StaffRequestDto> searchHidden(@Nullable Byte category,
                                               @Nullable Date start,
                                               @Nullable Date end,
                                               @Nullable String initiator,
                                               @Nullable Boolean rejected,
                                               @Nullable Byte nextApproval,
                                               @NotNull Integer index) {
        List<StaffRequest> requests = search(category, start, end, initiator, rejected, nextApproval, index);
        return StaffRequestDto.of(requests);
    }
}
